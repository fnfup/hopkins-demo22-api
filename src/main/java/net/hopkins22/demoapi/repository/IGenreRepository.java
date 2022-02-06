package net.hopkins22.demoapi.repository;

import net.hopkins22.demoapi.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IGenreRepository extends JpaRepository<Genre, Long> {

}
