package net.hopkins22.demoapi.controllers;

import net.hopkins22.demoapi.domain.LibraryStatusDto;
import net.hopkins22.demoapi.domain.LibraryStatusRequestDto;
import net.hopkins22.demoapi.domain.UserLibraryDto;
import net.hopkins22.demoapi.services.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "user")
@CrossOrigin(
        origins = { "localhost:8080", "localhost:4200",
                "https://hopkins-demo22-api-sc-hopkins-demo22-ui.azuremicroservices.io" })
public class AppUserController {

    @Autowired
    private AppUserService service;

    @GetMapping(path = "/library/{userId}")
    public UserLibraryDto getUserLibrary(@PathVariable Long userId) {
        return service.getUserMusic(userId);
    }

    @PutMapping(path = "/library/toggle/{trackId}")
    public void toggleUserMusicState(
            @PathVariable Long trackId) {
        service.toggleLibraryActiveState(trackId);
    }

    @PostMapping( path="/library/status" )
    public List<LibraryStatusDto> getLibraryStatus(
            @RequestBody LibraryStatusRequestDto requestDto) {
        return service.getMusicLibraryStatus(requestDto);
    }

}
