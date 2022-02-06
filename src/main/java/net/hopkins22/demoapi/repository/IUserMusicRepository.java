package net.hopkins22.demoapi.repository;

import net.hopkins22.demoapi.entity.UserMusic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IUserMusicRepository extends JpaRepository<UserMusic, Long> {

    @Query("SELECT m from UserMusic m WHERE m.user.id = :userId ")
    public List<UserMusic> findUserMusic(@Param("userId") Long userId);

    public List<UserMusic> findAllByUserId(Long userId);

}
