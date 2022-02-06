package net.hopkins22.demoapi.configs;

import net.hopkins22.demoapi.entity.AppUser;
import net.hopkins22.demoapi.repository.IAppUserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class AppUserConfig {

    @Bean("appuser_data")
    CommandLineRunner commandLineRunner(IAppUserRepository repo) {
        return args -> {

            AppUser testUser = new AppUser("hopkinsdemouser");
            AppUser tom = new AppUser("tombrady");

            repo.saveAll(List.of(tom, testUser));
        };
    }

}
