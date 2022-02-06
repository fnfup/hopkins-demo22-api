package net.hopkins22.demoapi.domain;

import net.hopkins22.demoapi.entity.UserOrder;

import java.util.List;

public class UserOrdersDto {

    private int count;
    private List<UserOrder> orders;

    public UserOrdersDto() {
    }

    public UserOrdersDto(List<UserOrder> orders) {
        this.count = orders.size();
        this.orders = orders;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<UserOrder> getOrders() {
        return orders;
    }

    public void setOrders(List<UserOrder> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "UserOrdersDto{" +
                "count=" + count +
                ", orders=" + orders +
                '}';
    }
}
