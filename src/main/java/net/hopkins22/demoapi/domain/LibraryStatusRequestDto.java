package net.hopkins22.demoapi.domain;

import java.util.Arrays;

public class LibraryStatusRequestDto {

    private Long userId;
    private Long[] trackIds;

    public LibraryStatusRequestDto() {
    }

    public LibraryStatusRequestDto(Long userId, Long[] trackIds) {
        this.userId = userId;
        this.trackIds = trackIds;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long[] getTrackIds() {
        return trackIds;
    }

    public void setTrackIds(Long[] trackIds) {
        this.trackIds = trackIds;
    }

    @Override
    public String toString() {
        return "LibraryStatusRequestDto{" +
                "userId=" + userId +
                ", trackIds=" + Arrays.toString(trackIds) +
                '}';
    }
}
