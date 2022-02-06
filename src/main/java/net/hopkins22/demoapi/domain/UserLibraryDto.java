package net.hopkins22.demoapi.domain;

import net.hopkins22.demoapi.entity.UserMusic;

import java.util.List;

public class UserLibraryDto {

    private int count;
    private List<UserMusic> library;

    public UserLibraryDto() {
    }

    public UserLibraryDto(List<UserMusic> library) {
        this.count = library.size();
        this.library = library;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<UserMusic> getLibrary() {
        return library;
    }

    public void setLibrary(List<UserMusic> library) {
        this.library = library;
    }

    @Override
    public String toString() {
        return "UserCatalogDto{" +
                "count=" + count +
                ", library=" + library +
                '}';
    }
}
