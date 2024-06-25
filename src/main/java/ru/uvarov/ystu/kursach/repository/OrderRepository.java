package ru.uvarov.ystu.kursach.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.uvarov.ystu.kursach.model.Order;

import java.time.LocalDateTime;

public interface OrderRepository extends JpaRepository<Order, Long>{

    //общее количество заказов за период
    @Query("SELECT COUNT(p) FROM order p WHERE p.dateAddedOrder BETWEEN :startDate AND :endDate")
    Long countOrdersBetweenDates(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);

    //сумма выручки за период
    @Query("SELECT SUM(p.item.price * p.countItemInOrder) FROM order p WHERE p.dateAddedOrder BETWEEN :startDate AND :endDate")
    Long totalRevenueBetweenDates(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);

}
