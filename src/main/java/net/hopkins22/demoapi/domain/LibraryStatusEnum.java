package net.hopkins22.demoapi.domain;

public enum LibraryStatusEnum {
    NONE("none"),
    ACTIVE("active"),
    INACTIVE("inactive");

    private String status;

    LibraryStatusEnum(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
