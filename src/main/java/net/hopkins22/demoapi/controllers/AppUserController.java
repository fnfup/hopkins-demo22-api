package net.hopkins22.demoapi.controllers;

import net.hopkins22.demoapi.entity.UserMusic;
import net.hopkins22.demoapi.services.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "user")
public class AppUserController {

    @Autowired
    private AppUserService service;

    @GetMapping(path = "/library/{userId}")
    public List<UserMusic> getUserLibrary(@PathVariable Long userId) {
        return service.getUserMusic(userId);
    }

    @PutMapping(path = "/library/toggle/{trackId}")
    public void toggleUserMusicState(
            @PathVariable Long trackId) {
        service.toggleLibraryActiveState(trackId);
    }


}
