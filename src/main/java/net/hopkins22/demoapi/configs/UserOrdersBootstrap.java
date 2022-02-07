package net.hopkins22.demoapi.configs;

import net.hopkins22.demoapi.entity.AppUser;
import net.hopkins22.demoapi.entity.MusicTrack;
import net.hopkins22.demoapi.entity.UserMusic;
import net.hopkins22.demoapi.repository.IAppUserRepository;
import net.hopkins22.demoapi.repository.IMusicTrackRepository;
import net.hopkins22.demoapi.repository.IUserMusicRepository;
import net.hopkins22.demoapi.repository.OrderAggregateRoot;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

public class UserOrdersBootstrap extends Thread {

    private final OrderAggregateRoot innerAggregateRoot;
    private final IAppUserRepository innerAppUserRepo;
    private final IMusicTrackRepository innerCatalogRepo;
    private final IUserMusicRepository innerUserMusicRepo;

    public UserOrdersBootstrap(
            OrderAggregateRoot aggregateRootCtr,
            IAppUserRepository appUserRepoCtr,
            IMusicTrackRepository catalogRepoCtr,
            IUserMusicRepository userMusicRepoCtr) {

        this.innerAggregateRoot = aggregateRootCtr;
        this.innerAppUserRepo = appUserRepoCtr;
        this.innerCatalogRepo = catalogRepoCtr;
        this.innerUserMusicRepo = userMusicRepoCtr;
    }

    @Override
    public synchronized void run() {

        System.out.println("----sT User Orders Ts-----");

        System.out.println("--- User Orders 5sec Wait ---");
        try {
            wait(5000L);
            executeInit();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private synchronized void executeInit() {
        Optional<AppUser> targetUser = innerAppUserRepo
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

            innerAggregateRoot.commitUserOrder(targetUser.get(), orderItems);
            System.out.println("----eT User Orders Te-----");
        }
    }

    private synchronized  List<MusicTrack> getRandomOrderList(Integer count, AppUser user) {

        List<UserMusic> currentLibrary = innerUserMusicRepo.findAllByUserId(user.getId());

        System.out.println("Current user library count: " + currentLibrary.size());

        System.out.println(
                currentLibrary.stream().reduce("",
                        (acc, curr) -> acc + "," +
                                String.valueOf(curr.getTrack().getId()), String::concat));

        List<MusicTrack> allMusic = innerCatalogRepo.findAll();

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
