package net.hopkins22.demoapi.configs;

import net.hopkins22.demoapi.entity.Artist;
import net.hopkins22.demoapi.entity.Genre;
import net.hopkins22.demoapi.entity.MusicTrack;
import net.hopkins22.demoapi.repository.IArtistRepository;
import net.hopkins22.demoapi.repository.IGenreRepository;
import net.hopkins22.demoapi.repository.IMusicTrackRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import java.util.*;

import static java.util.Map.entry;

@Configuration
public class MusicTrackConfig {



    @Bean("music_data")
    //@DependsOn({ "artist_data", "genre_data" })
    @Order(13)
    CommandLineRunner commandLineRunner(
            IMusicTrackRepository musicRepo,
            IArtistRepository artistRepo,
            IGenreRepository genreRepo) {

        return args -> {

            System.out.println("----s Music Tracks s-----");

            Thread musicTracksBootInit = new MusicTrackBootstrap(
                    musicRepo, artistRepo, genreRepo
            );
            musicTracksBootInit.start();

            System.out.println("----e Music Tracks e-----");
        };

    }


}
