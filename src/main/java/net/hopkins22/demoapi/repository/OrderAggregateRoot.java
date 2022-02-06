package net.hopkins22.demoapi.repository;

import net.hopkins22.demoapi.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Transactional
public class OrderAggregateRoot {

    @Autowired
    private IUserOrderRepository orderRepo;
    @Autowired
    private IOrderItemRepository orderItemRepo;
    @Autowired
    private IUserMusicRepository userMusicRepo;

    @Autowired
    public OrderAggregateRoot() {}

    public List<UserOrder> getAllOrders() {
        return orderRepo.findAll();
    }

    public List<UserOrder> getAllUserOrders(Long user_id) {
        return orderRepo.findAllByUserId(user_id);
    }

    /**
     * This ensures data consistency by aggregating an order with the appropriate
     * database updates to the UserOrder, OrderItems, and UserMusic tables
     * @param user
     * @param tracks
     */
    public void commitUserOrder(
            AppUser user,
            List<MusicTrack> tracks) {

        UserOrder order = new UserOrder(LocalDate.now(), tracks.size(), user);
        UserOrder savedOrder = orderRepo.save(order);

        List<OrderItem> orderItems = tracks.stream()
                .map(track -> new OrderItem(track, user, savedOrder))
                .collect(Collectors.toList());

        orderItemRepo.saveAll(orderItems);

        List<UserMusic> userMusic = tracks.stream()
                .map(track -> new UserMusic(true, user, track))
                .collect(Collectors.toList());

        userMusicRepo.saveAll(userMusic);
    }

}
