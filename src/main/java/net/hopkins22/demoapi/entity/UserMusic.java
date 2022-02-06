package net.hopkins22.demoapi.entity;

import javax.persistence.*;

@Entity
public class UserMusic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Boolean isActive;
    @ManyToOne
    private AppUser user;
    @ManyToOne
    private MusicTrack track;

    public UserMusic() {
    }

    public UserMusic(Boolean isActive, AppUser user, MusicTrack track) {
        this.isActive = isActive;
        this.user = user;
        this.track = track;
    }

    public UserMusic(Long id, Boolean isActive, AppUser user, MusicTrack track) {
        this.id = id;
        this.isActive = isActive;
        this.user = user;
        this.track = track;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public AppUser getUser() {
        return user;
    }

    public void setUser(AppUser user) {
        this.user = user;
    }

    public MusicTrack getTrack() {
        return track;
    }

    public void setTrack(MusicTrack track) {
        this.track = track;
    }

    @Override
    public String toString() {
        return "UserMusic{" +
                "id=" + id +
                ", isActive=" + isActive +
                ", user=" + user +
                ", track=" + track +
                '}';
    }
}
