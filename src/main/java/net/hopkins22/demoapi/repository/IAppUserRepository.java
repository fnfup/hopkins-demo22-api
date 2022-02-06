package net.hopkins22.demoapi.repository;

import net.hopkins22.demoapi.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IAppUserRepository extends JpaRepository<AppUser, Long> {

    public List<AppUser> findByUsername(String username);

}
