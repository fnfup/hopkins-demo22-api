package net.hopkins22.demoapi.services;

import net.hopkins22.demoapi.entity.UserMusic;
import net.hopkins22.demoapi.repository.IUserMusicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AppUserService {

    @Autowired
    private IUserMusicRepository userMusicRepo;

    public List<UserMusic> getUserMusic(Long userId) {
        return userMusicRepo.findAllByUserId(userId);
    }

    @Transactional
    public void toggleLibraryActiveState(Long trackId) {

        UserMusic record = userMusicRepo.findById(trackId)
            .orElseThrow(() -> new IllegalStateException(
                        "user music with id {" + trackId +
                                "} does not exist! Illegal operation"));

        boolean currVal = record.getActive();
        record.setActive(!currVal);
    }

}
