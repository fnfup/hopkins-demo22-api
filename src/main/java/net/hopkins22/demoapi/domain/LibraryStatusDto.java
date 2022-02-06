package net.hopkins22.demoapi.domain;

public class LibraryStatusDto {

    private Long trackId;
    private LibraryStatusEnum status;

    public LibraryStatusDto() {
    }

    public LibraryStatusDto(Long trackId, LibraryStatusEnum status) {
        this.trackId = trackId;
        this.status = status;
    }

    public Long getTrackId() {
        return trackId;
    }

    public void setTrackId(Long trackId) {
        this.trackId = trackId;
    }

    public LibraryStatusEnum getStatus() {
        return status;
    }

    public void setStatus(LibraryStatusEnum status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "LibraryStatusDto{" +
                "trackId=" + trackId +
                ", status=" + status +
                '}';
    }
}
