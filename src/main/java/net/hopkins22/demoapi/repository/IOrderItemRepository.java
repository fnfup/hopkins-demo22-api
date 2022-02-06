package net.hopkins22.demoapi.repository;

import net.hopkins22.demoapi.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IOrderItemRepository extends JpaRepository<OrderItem, Long> {

    @Query("SELECT i from OrderItem i WHERE i.userOrder.id = :orderId ")
    public List<OrderItem> findItemsByOrderId(@Param("orderId") Long orderId);

    public List<OrderItem> findAllByUserOrderId(Long orderId);

}
