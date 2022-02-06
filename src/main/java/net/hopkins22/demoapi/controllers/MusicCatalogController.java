package net.hopkins22.demoapi.controllers;

import net.hopkins22.demoapi.domain.RequestFilterDto;
import net.hopkins22.demoapi.entity.Artist;
import net.hopkins22.demoapi.entity.Genre;
import net.hopkins22.demoapi.entity.MusicTrack;
import net.hopkins22.demoapi.services.MusicCatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "catalog")
public class MusicCatalogController {

    @Autowired
    private MusicCatalogService service;

    public MusicCatalogController() {
    }

    @PostMapping( consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE } )
    public List<MusicTrack> getMusicCatalog(
            @RequestBody RequestFilterDto filter) {
        return service.searchMusicCatalog(filter);
    }

    @GetMapping( path = "/artist/{artistId}")
    public List<MusicTrack> getArtistCatalog(@PathVariable Long artistId) {
        return service.getMusicByArtist(artistId);
    }

    @GetMapping( path = "/genre/{genreId}")
    public List<MusicTrack> getGenreCatalog(@PathVariable Long genreId) {
        return service.getMusicByGenre(genreId);
    }

    @GetMapping( path = "/artist")
    public List<Artist> getArtists() {
        return service.getAvailableArtists();
    }

    @GetMapping( path = "/genre")
    public List<Genre> getGenres() {
        return service.getAvailableGenres();
    }


}
