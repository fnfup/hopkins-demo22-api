package net.hopkins22.demoapi.controllers;

import net.hopkins22.demoapi.domain.MusicOrderDto;
import net.hopkins22.demoapi.domain.UserOrdersDto;
import net.hopkins22.demoapi.entity.UserOrder;
import net.hopkins22.demoapi.services.UserOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "orders")
public class UserOrderController {

    @Autowired
    private UserOrderService service;

    @GetMapping
    public UserOrdersDto getAllUserOrders() {
        return service.getAllOrders();
    }

    @GetMapping(path = "/{userId}" )
    public UserOrdersDto getAllUserOrders(@PathVariable Long userId) {
        return service.getAllUserOrders(userId);
    }

    @PostMapping(path = "/purchase")
    public void orderSubmitted(@RequestBody MusicOrderDto orderRequest) {
        this.service.commitOrder(orderRequest);
    }

}
