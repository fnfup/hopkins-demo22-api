package net.hopkins22.demoapi.controllers;

import net.hopkins22.demoapi.domain.IAuthenticationFacade;
import net.hopkins22.demoapi.domain.MusicCatalogDto;
import net.hopkins22.demoapi.domain.RequestFilterDto;
import net.hopkins22.demoapi.entity.Artist;
import net.hopkins22.demoapi.entity.Genre;
import net.hopkins22.demoapi.entity.MusicTrack;
import net.hopkins22.demoapi.services.MusicCatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "catalog")
@CrossOrigin(
        origins = { "http://localhost:8080", "http://localhost:4200", "https://localhost:8443",
                "https://hopkins-demo22-api-sc-hopkins-demo22-ui.azuremicroservices.io" })
public class MusicCatalogController {

    @Autowired
    private MusicCatalogService service;
    @Autowired
    private IAuthenticationFacade authFacade;

    public MusicCatalogController() {
    }

    @PostMapping( consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE } )
    public MusicCatalogDto getMusicCatalog(
            @RequestBody RequestFilterDto filter) {
        return new MusicCatalogDto(service.searchMusicCatalog(filter));
    }

    @GetMapping( path = "/artist/{artistId}")
    public MusicCatalogDto getArtistCatalog(@PathVariable Long artistId) {
        return new MusicCatalogDto(service.getMusicByArtist(artistId));
    }

    @GetMapping( path = "/genre/{genreId}")
    public MusicCatalogDto getGenreCatalog(@PathVariable Long genreId) {
        return new MusicCatalogDto(service.getMusicByGenre(genreId));
    }


    @GetMapping( path = "/artist")
    public List<Artist> getArtists() {
        // Authentication auth = authFacade.getAuthentication(); // debugging and testing
        return service.getAvailableArtists();
    }

    @GetMapping( path = "/genre")
    public List<Genre> getGenres() {
        Authentication auth = authFacade.getAuthentication(); // debugging and testing
        return service.getAvailableGenres();
    }


}
