package net.hopkins22.demoapi.entity;

import javax.persistence.*;

@Entity
public class MusicTrack {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String album;
    @ManyToOne//( fetch = FetchType.EAGER )
    private Artist artist;
    @ManyToOne//( fetch = FetchType.EAGER )
    private Genre genre;

    public MusicTrack() {
    }

    public MusicTrack(String title, String album, Artist artist, Genre genre) {
        this.title = title;
        this.album = album;
        this.artist = artist;
        this.genre = genre;
    }

    public MusicTrack(Long id, String title, String album, Artist artist, Genre genre) {
        this.id = id;
        this.title = title;
        this.album = album;
        this.artist = artist;
        this.genre = genre;
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

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        return "MusicTrack{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", album='" + album + '\'' +
                ", artist=" + artist +
                ", genre=" + genre +
                '}';
    }
}
