package net.hopkins22.demoapi.configs;

import net.hopkins22.demoapi.entity.Genre;
import net.hopkins22.demoapi.repository.IGenreRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import java.util.List;

@Configuration
public class GenreConfig {

    @Bean("genre_data")
    @Order(12)
    CommandLineRunner commandLineRunner (IGenreRepository repository) {
        return args ->  {

            System.out.println("----s Music Genres s-----");

            Genre rock = new Genre("rock");
            Genre rap = new Genre("rap");
            Genre pop = new Genre("pop");
            Genre country = new Genre("country");
            Genre experimental = new Genre("experimental");
            Genre heavyMetal = new Genre("heavy metal");
            Genre rnb = new Genre("r&b");

            repository.saveAll(List.of(
                    rock, rap, pop, country, experimental, heavyMetal, rnb
            ));
            System.out.println("----e Music Genres e-----");
        };
    }

}
