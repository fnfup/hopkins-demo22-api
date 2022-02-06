package net.hopkins22.demoapi.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class UserOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date timestamp;
    private Integer itemCount;
    @ManyToOne( fetch = FetchType.EAGER )
    private AppUser user;
    @OneToMany( mappedBy = "userOrder", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<OrderItem> orderItems;

    public UserOrder() {
    }

    public UserOrder(Date timestamp, Integer itemCount, AppUser user) {
        this.timestamp = timestamp;
        this.itemCount = itemCount;
        this.user = user;
    }

    public UserOrder(Date timestamp, Integer itemCount, AppUser user, List<OrderItem> orderItems) {
        this.timestamp = timestamp;
        this.itemCount = itemCount;
        this.user = user;
        this.orderItems = orderItems;
    }

    public UserOrder(Long id, Date timestamp, Integer itemCount, AppUser user, List<OrderItem> orderItems) {
        this.id = id;
        this.timestamp = timestamp;
        this.itemCount = itemCount;
        this.user = user;
        this.orderItems = orderItems;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getItemCount() {
        return itemCount;
    }

    public void setItemCount(Integer itemCount) {
        this.itemCount = itemCount;
    }

    public AppUser getUser() {
        return user;
    }

    public void setUser(AppUser user) {
        this.user = user;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", timestamp=" + timestamp +
                ", itemCount=" + itemCount +
                ", user=" + user +
                ", orderItems=" + orderItems +
                '}';
    }
}
