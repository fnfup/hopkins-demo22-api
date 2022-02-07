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
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

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

//            Thread userOrdersBootInit = new UserOrdersBoostrapper(
//                    aggregateRoot, appUserRepo, catalogRepo, userMusicRepo);
//            userOrdersBootInit.start();

            System.out.println("----e User Orders e-----");

        };
    }

}
