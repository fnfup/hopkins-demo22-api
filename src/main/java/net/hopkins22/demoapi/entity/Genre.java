package net.hopkins22.demoapi.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany ( mappedBy = "genre", fetch = FetchType.EAGER )
    private List<MusicTrack> genreMusic;

    public Genre() {
    }

    public Genre(Long id, String name, List<MusicTrack> genreMusic) {
        this.id = id;
        this.name = name;
        this.genreMusic = genreMusic;
    }

    public Genre(String name, List<MusicTrack> genreMusic) {
        this.name = name;
        this.genreMusic = genreMusic;
    }

    public Genre(String name) {
        this.name = name;
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

    public List<MusicTrack> getGenreMusic() {
        return genreMusic;
    }

    public void setGenreMusic(List<MusicTrack> genreMusic) {
        this.genreMusic = genreMusic;
    }

    public boolean isGenre(String other) {
        return this.name.equalsIgnoreCase(other);
    }

    @Override
    public String toString() {
        return "Genre{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", genreMusicLength=" + genreMusic.size() +
                '}';
    }
}
