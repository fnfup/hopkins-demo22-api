package net.hopkins22.demoapi.configs;

import net.hopkins22.demoapi.entity.AppUser;
import net.hopkins22.demoapi.entity.Artist;
import net.hopkins22.demoapi.entity.MusicTrack;
import net.hopkins22.demoapi.entity.UserMusic;
import net.hopkins22.demoapi.repository.IAppUserRepository;
import net.hopkins22.demoapi.repository.IArtistRepository;
import net.hopkins22.demoapi.repository.IMusicTrackRepository;
import net.hopkins22.demoapi.repository.IUserMusicRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Configuration
public class UserMusicConfig {

    @Autowired
    private IAppUserRepository appUserRepo;
    @Autowired
    private IUserMusicRepository userMusicRepo;
    @Autowired
    private IMusicTrackRepository catalogRepo;

    @Bean("usermusic_data")
    @DependsOn({ "music_data", "appuser_data" })
    CommandLineRunner commandLineRunner() {

        return args -> {
//            System.out.println("---- User Music -----");

            Optional<AppUser> targetUser = appUserRepo
                    .findByUsername("hopkinsdemouser")
                    .stream().findFirst();

            if (targetUser.isPresent()) {
                List<UserMusic> library = getRandomSetOfMusic(6, targetUser.get());

//                for (UserMusic m: library) {
//                    System.out.println(m);
//                }

                userMusicRepo.saveAll(library);
            }
//            System.out.println("---- User Music -----");
        };

    }

    private List<UserMusic> getRandomSetOfMusic(Integer count, AppUser user) {
        List<MusicTrack> allMusic = catalogRepo.findAll();
        Random rand = new Random();

        List<UserMusic> userMusic = new ArrayList<>();

        // add 6 random songs to user library
        for(int i = 0; i < count; i++) {
            int randomIndex = rand.nextInt(allMusic.size());

            MusicTrack track = allMusic.get(randomIndex);
            UserMusic newEntry = new UserMusic(true, user, track);

            userMusic.add(newEntry);

            // remove so we don't have duplicates
            allMusic.remove(randomIndex);
        }

        return userMusic;
    }


}
