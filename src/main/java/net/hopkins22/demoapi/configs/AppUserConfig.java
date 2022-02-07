package net.hopkins22.demoapi.configs;

import net.hopkins22.demoapi.entity.AppUser;
import net.hopkins22.demoapi.repository.IAppUserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import java.util.List;

@Configuration
public class AppUserConfig {

    @Bean("appuser_data")
    @Order(10)
    CommandLineRunner commandLineRunner(IAppUserRepository repo) {
        return args -> {
            System.out.println("----s App Users s-----");
            AppUser testUser = new AppUser("hopkinsdemouser");
            AppUser tom = new AppUser("tombrady");

            repo.saveAll(List.of(tom, testUser));
            System.out.println("----e App Users e-----");
        };
    }

}
