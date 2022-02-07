package net.hopkins22.demoapi.configs;

import net.hopkins22.demoapi.entity.*;
import net.hopkins22.demoapi.repository.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.util.Map.entry;

public class MusicTrackBootstrap extends Thread {

    IArtistRepository artistRepo;
    IMusicTrackRepository musicRepo;
    IGenreRepository genreRepo;

    List<Artist> artists;
    List<Genre> genres;
    List<MusicTrack> musicTracks;

    public MusicTrackBootstrap(
            IMusicTrackRepository musicRepo,
            IArtistRepository artistRepo,
            IGenreRepository genreRepo) {

        this.artistRepo = artistRepo;
        this.musicRepo = musicRepo;
        this.genreRepo = genreRepo;
    }

    @Override
    public synchronized void run() {

        System.out.println("----sT Music Tracks Ts-----");

        System.out.println("--- Music Tracks 4sec Wait ---");
        try {
            wait(4000L);
            executeInit();
            System.out.println("----eT Music Tracks Te-----");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void executeInit() {

        this.artists = artistRepo.findAll();
        this.genres = genreRepo.findAll();
        this.musicTracks = new ArrayList<>();

        for (Artist a : artists) {
            switch (a.getName()) {
                case "drake": {
                    Map<String, String> artistMusic = Map.ofEntries(
                            entry("one dance", "views"),
                            entry("passion fruit", "more life"),
                            entry("nonstop", "scorpion")
                    );
                    buildArtistCollection(a, "rap", artistMusic);

                    Map<String, String> rnbMusic = Map.ofEntries(
                            entry("teenage fever", "more life"),
                            entry("marvins room", "take care")
                    );
                    buildArtistCollection(a, "r&b", rnbMusic);
                    break;
                }
                case "adele": {
                    Map<String, String> artistMusic = Map.ofEntries(
                            entry("hello", "25"),
                            entry("rolling in the deep", "21"),
                            entry("make you feel my love", "19")
                    );

                    buildArtistCollection(a, "pop", artistMusic);
                    break;
                }
                case "chris stapleton": {
                    Map<String, String> artistMusic = Map.ofEntries(
                            entry("tennessee whiskey", "traveller"),
                            entry("fire way", "traveller"),
                            entry("the devil i named music", "traveller")
                    );

                    buildArtistCollection(a, "country", artistMusic);
                    break;
                }
                case "david bowie": {
                    Map<String, String> artistMusic = Map.ofEntries(
                            entry("changes", "hunky dory"),
                            entry("young americans", "young americans"),
                            entry("ashes to ashes", "tears for fears")
                    );
                    buildArtistCollection(a, "experimental", artistMusic);

                    Map<String, String> rockMusic = Map.ofEntries(
                            entry("china girl", "lets dance"),
                            entry("wild is the wind", "station to station"),
                            entry("cat people", "cat people")
                    );
                    buildArtistCollection(a, "rock", rockMusic);

                    break;
                }
                case "beatles": {
                    Map<String, String> artistMusic = Map.ofEntries(
                            entry("tomorrow never knows", "revolver"),
                            entry("hey jude", "single"),
                            entry("something", "abbey road")
                    );
                    buildArtistCollection(a, "rock", artistMusic);

                    Map<String, String> popMusic = Map.ofEntries(
                            entry("she loves you", "pat masters"),
                            entry("penny lane", "magical mystery tour")
                    );
                    buildArtistCollection(a, "pop", popMusic);
                    break;
                }
                case "led zeppelin": {
                    Map<String, String> artistMusic = Map.ofEntries(
                            entry("stairway to heavan", "led zeppelin iv"),
                            entry("in my time of dying", "physical grafitti"),
                            entry("the rain song", "houses of holy")
                    );

                    buildArtistCollection(a, "heavy metal", artistMusic);
                    break;
                }
                case "alicia keys": {
                    Map<String, String> artistMusic = Map.ofEntries(
                            entry("if i ain't got you", "the diary of alicia keys"),
                            entry("no one", "the essential ballads"),
                            entry("fallin", "songs in a minor")
                    );

                    buildArtistCollection(a, "r&b", artistMusic);
                    break;
                }
            }
        }

        musicRepo.saveAll(musicTracks);
    }

    private void buildArtistCollection(
            Artist artist, String genre, Map<String, String> tracks) {

        Optional<Genre> artistGenre = genres.stream()
                .filter(g -> g.isGenre(genre))
                .findFirst();

        if (artistGenre.isPresent()) {
            for (Map.Entry<String, String> t : tracks.entrySet()) {
                musicTracks.add(
                        new MusicTrack(t.getKey(), t.getValue(), artist, artistGenre.get())
                );
            }
        }

    }

}
