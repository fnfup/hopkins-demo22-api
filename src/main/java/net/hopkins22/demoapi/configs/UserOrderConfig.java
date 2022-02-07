package net.hopkins22.demoapi.configs;

import net.hopkins22.demoapi.entity.AppUser;
import net.hopkins22.demoapi.entity.MusicTrack;
import net.hopkins22.demoapi.entity.UserMusic;
import net.hopkins22.demoapi.repository.IAppUserRepository;
import net.hopkins22.demoapi.repository.IMusicTrackRepository;
import net.hopkins22.demoapi.repository.IUserMusicRepository;
import net.hopkins22.demoapi.repository.OrderAggregateRoot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Configuration
@Order(Ordered.LOWEST_PRECEDENCE)
public class UserOrderConfig {

    @Autowired
    private IAppUserRepository appUserRepo;
    @Autowired
    private IUserMusicRepository userMusicRepo;
    @Autowired
    private IMusicTrackRepository catalogRepo;
    @Autowired
    private OrderAggregateRoot aggregateRoot;

    @Bean("userorder_data")
    //@DependsOn({ "artist_data", "genre_data", "appuser_data", "music_data", "usermusic_data" })
    @Order(Ordered.LOWEST_PRECEDENCE)
    CommandLineRunner commandLineRunner() {
        return args -> {

            System.out.println("----s User Orders s-----");

            Optional<AppUser> targetUser = appUserRepo
                    .findByUsername("hopkinsdemouser")
                    .stream().findFirst();

            if (targetUser.isPresent()) {
                List<MusicTrack> orderItems = getRandomOrderList(4, targetUser.get());

//                for (MusicTrack m: orderItems) {
//                    System.out.println(m);
//                }
                System.out.println(
                    orderItems.stream().reduce("",
                            (acc, curr) -> acc + "," +
                                    String.valueOf(curr.getId()), String::concat));

                aggregateRoot.commitUserOrder(targetUser.get(), orderItems);
            }
            System.out.println("----e User Orders e-----");

        };
    }

    private List<MusicTrack> getRandomOrderList(Integer count, AppUser user) {

        List<UserMusic> currentLibrary = userMusicRepo.findAllByUserId(user.getId());

        System.out.println("Current user library count: " + currentLibrary.size());

        System.out.println(
                currentLibrary.stream().reduce("",
                        (acc, curr) -> acc + "," +
                                String.valueOf(curr.getTrack().getId()), String::concat));

        List<MusicTrack> allMusic = catalogRepo.findAll();

        List<MusicTrack> filteredMusic = allMusic.stream().filter(track -> {
            return !currentLibrary.stream().anyMatch(libraryTrack -> {
                return track.getId() == libraryTrack.getTrack().getId();
            });
        }).collect(Collectors.toList());

        Random rand = new Random();

        List<MusicTrack> orderList = new ArrayList<>();

        for(int i = 0; i < count; i++) {
            int randomIndex = rand.nextInt(filteredMusic.size());

            MusicTrack track = filteredMusic.get(randomIndex);
            orderList.add(track);

            // remove so we don't have duplicates
            filteredMusic.remove(randomIndex);
        }

        return orderList;
    }


}
