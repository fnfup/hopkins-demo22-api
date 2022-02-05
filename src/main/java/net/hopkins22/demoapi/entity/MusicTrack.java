package net.hopkins22.demoapi.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class MusicTrack {

    @Id
    @GeneratedValue
    private Long id;
    private String title;
    @ManyToOne
    private Artist artist;

    public MusicTrack() {
    }

    public MusicTrack(Long id, String title, Artist artist) {
        this.id = id;
        this.title = title;
        this.artist = artist;
    }

    public MusicTrack(String title, Artist artist) {
        this.title = title;
        this.artist = artist;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    @Override
    public String toString() {
        return "MusicTrack{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", artist=" + artist +
                '}';
    }
}
