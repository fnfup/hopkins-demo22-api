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
@CrossOrigin(
        origins = { "http://localhost:8080", "http://localhost:4200", "https://localhost:8443",
                "https://hopkins-demo22-api-sc-hopkins-demo22-ui.azuremicroservices.io" })
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
