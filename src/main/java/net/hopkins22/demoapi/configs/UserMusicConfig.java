package net.hopkins22.demoapi.configs;

import net.hopkins22.demoapi.entity.AppUser;
import net.hopkins22.demoapi.entity.MusicTrack;
import net.hopkins22.demoapi.entity.UserMusic;
import net.hopkins22.demoapi.repository.IAppUserRepository;
import net.hopkins22.demoapi.repository.IMusicTrackRepository;
import net.hopkins22.demoapi.repository.IUserMusicRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Configuration
public class UserMusicConfig {

    @Bean("usermusic_data")
    //@DependsOn({ "artist_data", "genre_data", "appuser_data", "music_data" })
    @Order(15)
    CommandLineRunner commandLineRunner(
        IAppUserRepository appUserRepo,
        IUserMusicRepository userMusicRepo,
        IMusicTrackRepository catalogRepo) {

        return args -> {
            System.out.println("----s User Music s-----");

            Optional<AppUser> targetUser = appUserRepo
                    .findByUsername("hopkinsdemouser")
                    .stream().findFirst();

            if (targetUser.isPresent()) {
                List<UserMusic> library = getRandomSetOfMusic(
                    catalogRepo, 6, targetUser.get());

//                for (UserMusic m: library) {
//                    System.out.println(m);
//                }

                userMusicRepo.saveAll(library);
            }
            System.out.println("----e User Music e-----");
        };

    }

    private List<UserMusic> getRandomSetOfMusic(
        IMusicTrackRepository catalogRepo,
        Integer count, AppUser user) {
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
