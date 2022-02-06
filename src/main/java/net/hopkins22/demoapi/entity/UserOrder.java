package net.hopkins22.demoapi.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class UserOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate timestamp;
    private UUID orderUID;
    private Integer itemCount;
    @ManyToOne( fetch = FetchType.EAGER )
    private AppUser user;
    // @OneToMany( mappedBy = "userOrder", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @OneToMany( cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<OrderItem> orderItems;

    public UserOrder() {
    }

    public UserOrder(LocalDate timestamp, Integer itemCount, AppUser user, List<OrderItem> orderItems) {
        this.timestamp = timestamp;
        this.itemCount = itemCount;
        this.user = user;
        this.orderItems = orderItems;
        this.setupUUID();
    }

    public UserOrder(Long id, LocalDate timestamp, Integer itemCount, AppUser user, List<OrderItem> orderItems) {
        this.id = id;
        this.timestamp = timestamp;
        this.itemCount = itemCount;
        this.user = user;
        this.orderItems = orderItems;
        this.setupUUID();
    }

    private void setupUUID() {
        this.orderUID = UUID.randomUUID();
        this.orderItems.stream().forEach(item -> { item.setOrderUID( this.orderUID );});
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getTimestamp() {
        return timestamp;
    }

    public UUID getOrderUID() {
        return orderUID;
    }

    public void setOrderUID(UUID orderUID) {
        this.orderUID = orderUID;
    }

    public void setTimestamp(LocalDate timestamp) {
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
                ", orderUID=" + orderUID +
                ", itemCount=" + itemCount +
                ", user=" + user +
                ", orderItems=[" + orderItems.size() + "]" +
                '}';
    }
}
