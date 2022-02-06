package net.hopkins22.demoapi.repository;

import net.hopkins22.demoapi.entity.Artist;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IArtistRepository extends JpaRepository<Artist, Long> {

    public List<Artist> findAllByNameIn(List<String> names);

}
