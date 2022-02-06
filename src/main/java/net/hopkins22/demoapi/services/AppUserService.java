package net.hopkins22.demoapi.services;

import net.hopkins22.demoapi.domain.LibraryStatusDto;
import net.hopkins22.demoapi.domain.LibraryStatusEnum;
import net.hopkins22.demoapi.domain.LibraryStatusRequestDto;
import net.hopkins22.demoapi.domain.UserLibraryDto;
import net.hopkins22.demoapi.entity.AppUser;
import net.hopkins22.demoapi.entity.MusicTrack;
import net.hopkins22.demoapi.entity.UserMusic;
import net.hopkins22.demoapi.repository.IAppUserRepository;
import net.hopkins22.demoapi.repository.IUserMusicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AppUserService {

    @Autowired
    private IUserMusicRepository userMusicRepo;
    @Autowired
    private IAppUserRepository appUserRepo;

    public UserLibraryDto getUserMusic(Long userId) {
        return new UserLibraryDto(
                userMusicRepo.findAllByUserId(userId));
    }

    @Transactional
    public void toggleLibraryActiveState(Long trackId) {

        UserMusic record = userMusicRepo.findById(trackId)
            .orElseThrow(() -> new IllegalStateException(
                        "user music with id {" + trackId +
                                "} does not exist! Illegal operation"));

        boolean currVal = record.getActive();
        record.setActive(!currVal);
    }

    public List<LibraryStatusDto> getMusicLibraryStatus (LibraryStatusRequestDto request) {

        AppUser user = getUser(request.getUserId());

        List<UserMusic> currentLibrary = userMusicRepo.findAllByUserId(request.getUserId());

        List<Long> trackIdList = List.of(request.getTrackIds());

        var statusList = trackIdList.stream().map(trackId -> {
            LibraryStatusDto status = new LibraryStatusDto();
            status.setTrackId(trackId);
            var matched = currentLibrary.stream()
                    .filter(entry -> trackId == entry.getTrack().getId())
                    .collect(Collectors.toList());

            if (matched.size() > 0) {
                if (matched.get(0).getActive()) {
                    status.setStatus(LibraryStatusEnum.ACTIVE);
                } else {
                    status.setStatus(LibraryStatusEnum.INACTIVE);
                }
            } else {
                status.setStatus(LibraryStatusEnum.NONE);
            }
            return status;
        }).collect(Collectors.toList());

        return statusList;
    }

    private AppUser getUser(Long userId) {
        Optional<AppUser> targetUser = appUserRepo
                .findById(userId)
                .stream().findFirst();

        if (!targetUser.isPresent()) {
            throw new IllegalStateException(
                    "Cannot query user music library for invalid user id: " + userId);
        }

        return targetUser.get();
    }

}
