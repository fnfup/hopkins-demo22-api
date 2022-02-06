package net.hopkins22.demoapi.configs;

import net.hopkins22.demoapi.entity.Artist;
import net.hopkins22.demoapi.repository.IArtistRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ArtistConfig {

    @Bean("artist_data")
    CommandLineRunner commandLineRunner(IArtistRepository repository) {
        return args -> {
            Artist drake = new Artist("drake", true);
            Artist adele = new Artist("adele", true);
            Artist chris = new Artist("chris stapleton", true);
            Artist davidBowie = new Artist("david bowie", false);
            Artist beatles = new Artist("beatles", false);
            Artist zeppelin = new Artist("led zeppelin", false);
            Artist alicia = new Artist("alicia keys", false);

            repository.saveAll(List.of(
               drake, adele, chris, davidBowie, beatles, zeppelin, alicia
            ));
        };
    }


}
