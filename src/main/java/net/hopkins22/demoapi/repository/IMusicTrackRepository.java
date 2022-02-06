package net.hopkins22.demoapi.repository;

import net.hopkins22.demoapi.entity.MusicTrack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IMusicTrackRepository extends JpaRepository<MusicTrack, Long> {

    @Query("SELECT t from MusicTrack t WHERE t.artist.id = :artistId ")
    public List<MusicTrack> findMusicByArtistId(@Param("artistId") Long artistId);

    @Query("SELECT t from MusicTrack t WHERE t.artist.id IN (:artistIds) ")
    public List<MusicTrack> findMusicByArtistIds(@Param("artistIds") List<Long> artistIds);

    @Query("SELECT t from MusicTrack t WHERE t.genre.id = :genreId ")
    public List<MusicTrack> findMusicByGenreId(@Param("genreId") Long genreId);

    @Query("SELECT t from MusicTrack t WHERE t.genre.id IN (:genreIds) ")
    public List<MusicTrack> findMusicByGenreIds(@Param("genreIds") List<Long> genreIds);

    @Query("SELECT t from MusicTrack t " +
            "WHERE t.genre.id IN (:genreIds) " +
            "AND  t.artist.id IN (:artistIds)")
    public List<MusicTrack> findByArtistAndGenreIds(
            @Param("artistIds") List<Long> artistIds,
            @Param("genreIds") List<Long> genreIds);

}
