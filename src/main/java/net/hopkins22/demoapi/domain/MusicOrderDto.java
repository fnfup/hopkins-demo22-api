package net.hopkins22.demoapi.domain;

import org.bouncycastle.util.Arrays;
import org.springframework.util.Assert;

public class MusicOrderDto {

    private Long userId;
    private Long[] items;

    public MusicOrderDto() {
    }

    public MusicOrderDto(Long userId) {
        this.userId = userId;
    }

    public MusicOrderDto(Long userId, Long[] items) {
        this.userId = userId;
        this.items = items;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long[] getItems() {
        return items;
    }

    public void setItems(Long[] items) {
        this.items = items;
    }

    public static boolean isValid(MusicOrderDto other) {
        Assert.notNull(other, "Null purchase request object");
        Assert.notNull(other.userId, "Null purchase userId field in request object");
        Assert.notNull(other.items, "Null purchase userId field in request object");
        Assert.isTrue(other.userId > 0, "Invalid purchase user id");
        Assert.isTrue(!Arrays.isNullOrEmpty(other.items), "List of purchase orders cannot be empty");
        Assert.noNullElements(other.items, "Invalid/null list of purchase orders");
        return true;
    }
}
