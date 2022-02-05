package net.hopkins22.demoapi.entity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IMusicTrackRepository extends JpaRepository<MusicTrack, Long> {
}
