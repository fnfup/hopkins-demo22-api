package net.hopkins22.demoapi.configs;

import net.hopkins22.demoapi.entity.Artist;
import net.hopkins22.demoapi.entity.IArtistRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ArtistConfig {

    @Bean
    CommandLineRunner commandLineRunner(IArtistRepository repository) {
        return args -> {
            Artist drake = new Artist("drake", true);
            Artist adele = new Artist("adele", true);
            Artist chris = new Artist("chris stapleton", true);
            Artist davidBowie = new Artist("dave bowie", false);
            Artist beatles = new Artist("beatles", false);

            repository.saveAll(List.of(
               drake, adele, chris, davidBowie, beatles
            ));
        };
    }


}
