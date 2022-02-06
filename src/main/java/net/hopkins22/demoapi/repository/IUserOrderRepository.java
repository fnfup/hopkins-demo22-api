package net.hopkins22.demoapi.repository;

import net.hopkins22.demoapi.entity.UserOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IUserOrderRepository extends JpaRepository<UserOrder, Long> {

        @Query("SELECT o from UserOrder o WHERE o.user.id = :userId ")
        public List<UserOrder> findUserOrders(@Param("userId") Long userId);

        public List<UserOrder> findAllByUserId(Long userId);

}
