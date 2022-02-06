package net.hopkins22.demoapi.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    @OneToMany( mappedBy = "user" )
    private List<UserMusic> music;
    @OneToMany( mappedBy = "user" )
    private List<UserOrder> userOrders;

    public AppUser() {
    }

    public AppUser(String name) {
        this.username = name;
    }

    public AppUser(String name, List<UserMusic> music, List<UserOrder> userOrders) {
        this.username = name;
        this.music = music;
        this.userOrders = userOrders;
    }

    public AppUser(Long id, String name, List<UserMusic> music, List<UserOrder> userOrders) {
        this.id = id;
        this.username = name;
        this.music = music;
        this.userOrders = userOrders;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<UserMusic> getMusic() {
        return music;
    }

    public void setMusic(List<UserMusic> music) {
        this.music = music;
    }

    public List<UserOrder> getOrders() {
        return userOrders;
    }

    public void setOrders(List<UserOrder> userOrders) {
        this.userOrders = userOrders;
    }

    @Override
    public String toString() {
        return "AppUser{" +
                "id=" + id +
                ", name='" + username + '\'' +
                '}';
    }
}
