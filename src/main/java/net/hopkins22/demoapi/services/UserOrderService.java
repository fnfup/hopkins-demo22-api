package net.hopkins22.demoapi.services;

import net.hopkins22.demoapi.domain.MusicOrderDto;
import net.hopkins22.demoapi.domain.UserOrdersDto;
import net.hopkins22.demoapi.entity.AppUser;
import net.hopkins22.demoapi.entity.MusicTrack;
import net.hopkins22.demoapi.entity.UserMusic;
import net.hopkins22.demoapi.entity.UserOrder;
import net.hopkins22.demoapi.repository.IAppUserRepository;
import net.hopkins22.demoapi.repository.IMusicTrackRepository;
import net.hopkins22.demoapi.repository.IUserMusicRepository;
import net.hopkins22.demoapi.repository.OrderAggregateRoot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
public class UserOrderService {

    @Autowired
    private OrderAggregateRoot aggregateRoot;
    @Autowired
    private IAppUserRepository appUserRepo;
    @Autowired
    private IUserMusicRepository userMusicRepo;
    @Autowired
    private IMusicTrackRepository catalogRepo;

    public UserOrdersDto getAllOrders() {
        return new UserOrdersDto(
                aggregateRoot.getAllOrders());
    }

    public UserOrdersDto getAllUserOrders(Long userId) {
        return new UserOrdersDto(aggregateRoot.getAllUserOrders(userId));
    }

    public void commitOrder(MusicOrderDto orderRequest) {
        if (MusicOrderDto.isValid(orderRequest)) {

            AppUser user = getUser(orderRequest.getUserId());
            List<MusicTrack> requestedMusic = getRequestedMusic(orderRequest.getItems());
            checkForDuplicatePurchase(requestedMusic, orderRequest.getUserId());

            aggregateRoot.commitUserOrder(user, requestedMusic);
        }
    }

    private AppUser getUser(Long userId) {
        Optional<AppUser> targetUser = appUserRepo
                .findById(userId)
                .stream().findFirst();

        if (!targetUser.isPresent()) {
            throw new IllegalStateException(
                    "Cannot submit order for invalid user id: " + userId);
        }

        return targetUser.get();
    }

    private List<MusicTrack> getRequestedMusic(Long[] trackIds) {
        List<Long> trackIdList = List.of(trackIds);

        List<MusicTrack> requestedMusic = catalogRepo.findAllById(trackIdList);

        if (trackIds.length != requestedMusic.size()) {
            var invalidIds = trackIdList.stream()
                    .filter(requestId -> {

                        return !requestedMusic.stream()
                                .anyMatch(track -> track.getId() == requestId);

                    }).reduce("",
                            (acc, id) -> acc + "," + String.valueOf((id)),
                            String::concat);

            throw new IllegalStateException("Invalid order items found! " +  invalidIds);
        }

        return requestedMusic;
    }

    private void checkForDuplicatePurchase(List<MusicTrack> tracks, Long userId) {

        List<UserMusic> currentLibrary = userMusicRepo.findAllByUserId(userId);

        var duplicates = tracks.stream().filter(track -> {
            boolean result = currentLibrary.stream()
                    .anyMatch(curr -> curr.getTrack().getId() == track.getId());
            System.out.println();
            return result;
        }).collect(Collectors.toList());

        if(duplicates.size() > 0) {
            var duplicateStr = duplicates.stream().reduce("",
                    (acc, curr) -> acc + ", " + curr.getId() + ":" + curr.getTitle()
                    , String::concat);

            throw new IllegalStateException("Duplicate purchase order items not allowed: " +  duplicateStr);
        }
    }

}
