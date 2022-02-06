package net.hopkins22.demoapi.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.UUID;

@Entity
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private UUID orderUID;
    @ManyToOne
    private MusicTrack track;
    @ManyToOne
    private AppUser user;
//    @ManyToOne
//    private UserOrder userOrder;

    public OrderItem() {
    }

    public OrderItem(Long id, MusicTrack track, AppUser user) {
        this.id = id;
        this.track = track;
        this.user = user;
    }

//    public OrderItem(Long id, UUID orderUID, MusicTrack track, AppUser user, UserOrder userOrder) {
//        this.id = id;
//        this.orderUID = orderUID;
//        this.track = track;
//        this.user = user;
//        this.userOrder = userOrder;
//    }

    public OrderItem(MusicTrack track, AppUser user) {
        this.track = track;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UUID getOrderUID() {
        return orderUID;
    }

    public void setOrderUID(UUID orderUID) {
        this.orderUID = orderUID;
    }

    public MusicTrack getTrack() {
        return track;
    }

    public void setTrack(MusicTrack track) {
        this.track = track;
    }

    public AppUser getUser() {
        return user;
    }

    public void setUser(AppUser user) {
        this.user = user;
    }

//    public UserOrder getOrder() {
//        return userOrder;
//    }
//
//    public void setOrder(UserOrder userOrder) {
//        this.userOrder = userOrder;
//    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "id=" + id +
                ", track=" + track +
                ", user=" + user +
                ", orderUID=" + orderUID +
                 //", order=" + userOrder.getId() +
                '}';
    }
}
