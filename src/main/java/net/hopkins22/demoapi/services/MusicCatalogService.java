package net.hopkins22.demoapi.services;

import net.hopkins22.demoapi.domain.RequestFilterDto;
import net.hopkins22.demoapi.domain.SearchType;
import net.hopkins22.demoapi.entity.Artist;
import net.hopkins22.demoapi.entity.Genre;
import net.hopkins22.demoapi.entity.MusicTrack;
import net.hopkins22.demoapi.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MusicCatalogService {

    @Autowired
    private IMusicTrackRepository musicRepo;
    @Autowired
    private IArtistRepository artistRepo;
    @Autowired
    private IGenreRepository genreRepo;

    public MusicCatalogService() {}

    public List<MusicTrack> searchMusicCatalog(RequestFilterDto filter) {

        SearchType search = getSearchType(filter);

        List<MusicTrack> results;

        switch (search) {
//            case EMPTY:
//                results = musicRepo.findAll();
//                break;
            case ARTIST_ONLY:
                results = musicRepo.findMusicByArtistIds(List.of(filter.getArtist()));
                break;
            case GENRE_ONLY:
                results = musicRepo.findMusicByGenreIds(List.of(filter.getGenre()));
                break;
            case ALL_CONDITIONS:
                results = musicRepo.findByArtistAndGenreIds(
                        List.of(filter.getArtist()),
                        List.of(filter.getGenre()));
                break;
            default:
                results = musicRepo.findAll();
                break;
        }

        return results;
    }

    public List<Artist> getAvailableArtists() {
        return artistRepo.findAll();
    }

    public List<MusicTrack> getMusicByArtist(Long artistId) {
        return musicRepo.findMusicByArtistId(artistId);
    }

    public List<Genre> getAvailableGenres() {
        return genreRepo.findAll();
    }

    public List<MusicTrack> getMusicByGenre(Long genreId) {
        return musicRepo.findMusicByArtistId(genreId);
    }

    private SearchType getSearchType(RequestFilterDto filter) {
        int cutOff = 4;
        boolean exit = false;
        int curr = 0;
        SearchType returnVal = SearchType.EMPTY;

        while (curr < cutOff && !exit) {

            switch (curr) {
                case 0:
                    if (RequestFilterDto.isEmpty(filter)) {
                        exit = true;
                        returnVal = SearchType.EMPTY;
                    }
                    break;
                case 1:
                    if (RequestFilterDto.isArtistOnlyValues(filter)) {
                        exit = true;
                        returnVal = SearchType.ARTIST_ONLY;
                    }
                    break;
                case 2:
                    if (RequestFilterDto.isGenreOnlyValues(filter)) {
                        exit = true;
                        returnVal = SearchType.GENRE_ONLY;
                    }
                    break;
                case 3:
                    exit = true;
                    returnVal = SearchType.ALL_CONDITIONS;
                    break;
                default:
                    break;
            }
            curr++;
        }

        return returnVal;
    }

}
