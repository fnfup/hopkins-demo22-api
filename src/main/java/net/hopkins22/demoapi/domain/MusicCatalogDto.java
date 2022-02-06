package net.hopkins22.demoapi.domain;

import net.hopkins22.demoapi.entity.MusicTrack;

import java.util.List;

public class MusicCatalogDto {

    private Integer count;
    private List<MusicTrack> music;

    public MusicCatalogDto() {}

    public MusicCatalogDto(List<MusicTrack> music) {
        this.count = music.size();
        this.music = music;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<MusicTrack> getMusic() {
        return music;
    }

    public void setMusic(List<MusicTrack> music) {
        this.music = music;
    }

    @Override
    public String toString() {
        return "{" +
                "\"count: \"" + count +
                ", \"music\": " + music +
                '}';
    }
}
