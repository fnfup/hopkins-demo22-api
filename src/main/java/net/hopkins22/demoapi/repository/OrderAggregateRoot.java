package net.hopkins22.demoapi.repository;

import net.hopkins22.demoapi.entity.UserOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class OrderAggregateRoot {

    @Autowired
    private IUserOrderRepository orderRepo;
    @Autowired
    private IOrderItemRepository orderItemRepo;

    @Autowired
    public OrderAggregateRoot() {}

    public List<UserOrder> getAllOrders() {
        return orderRepo.findAll();
    }

    public List<UserOrder> getAllUserOrders(Long user_id) {
        return orderRepo.findAllByUserId(user_id);
    }

}
