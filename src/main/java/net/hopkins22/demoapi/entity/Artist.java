package net.hopkins22.demoapi.entity;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Artist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
//    @OneToMany ( mappedBy = "artist", fetch = FetchType.LAZY )
//    private List<MusicTrack> artistMusic;
    private boolean isActive;

    public Artist() {
    }

    public Artist(Long id, String name, List<MusicTrack> artistMusic, boolean isActive) {
        this.id = id;
        this.name = name;
        //this.artistMusic = artistMusic;
        this.isActive = isActive;
    }

    public Artist(String name, List<MusicTrack> artistMusic, boolean isActive) {
        this.name = name;
        //this.artistMusic = artistMusic;
        this.isActive = isActive;
    }

    public Artist(String name, boolean isActive) {
        this.name = name;
        this.isActive = isActive;
    }

    public Artist(Long id, String name, boolean isActive) {
        this.id = id;
        this.name = name;
        this.isActive = isActive;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public List<MusicTrack> getArtistMusic() {
//        return artistMusic;
//    }
//
//    public void setArtistMusic(List<MusicTrack> artistMusic) {
//        this.artistMusic = artistMusic;
//    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    @Override
    public String toString() {
        return "Artist{" +
                "id=" + id +
                ", name='" + name + '\'' +
                //", artistMusicLength=" + artistMusic.size() +
                ", isActive=" + isActive +
                '}';
    }
}
