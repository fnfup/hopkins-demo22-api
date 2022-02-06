package net.hopkins22.demoapi.domain;

import org.springframework.util.Assert;

public class MusicOrderDto {

    private Long userId;
    private Integer[] items;

    public MusicOrderDto() {
    }

    public MusicOrderDto(Long userId) {
        this.userId = userId;
    }

    public MusicOrderDto(Long userId, Integer[] items) {
        this.userId = userId;
        this.items = items;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer[] getItems() {
        return items;
    }

    public void setItems(Integer[] items) {
        this.items = items;
    }

    public boolean isValid() {
        Assert.isTrue(this.userId > 0, "Invalid purchase user id");
        Assert.notEmpty(this.items, "List of purchase orders cannot be empty");
        return true;
    }
}
