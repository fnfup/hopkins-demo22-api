package net.hopkins22.demoapi.configs;

import net.hopkins22.demoapi.repository.IAppUserRepository;
import net.hopkins22.demoapi.repository.IMusicTrackRepository;
import net.hopkins22.demoapi.repository.IUserMusicRepository;
import net.hopkins22.demoapi.repository.OrderAggregateRoot;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

@Configuration
@Order(Ordered.LOWEST_PRECEDENCE)
public class UserOrderConfig {

    @Bean("userorder_data")
    @Order(20)
    CommandLineRunner commandLineRunner(
            IAppUserRepository appUserRepo,
            IUserMusicRepository userMusicRepo,
            IMusicTrackRepository catalogRepo,
            OrderAggregateRoot aggregateRoot) {
        return args -> {

            System.out.println("----s User Orders s-----");

            Thread userOrdersBootInit = new UserOrdersBootstrap(
                    aggregateRoot, appUserRepo, catalogRepo, userMusicRepo);
            userOrdersBootInit.start();

            System.out.println("----e User Orders e-----");

        };
    }

}
