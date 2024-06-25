package ru.uvarov.ystu.kursach.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import ru.uvarov.ystu.kursach.model.Item;
import ru.uvarov.ystu.kursach.model.ItemWithCount;

import java.util.List;

public interface ItemRepository extends PagingAndSortingRepository<Item, Long>, JpaRepository<Item, Long> {

    @Query("SELECT new ru.uvarov.ystu.kursach.model.ItemWithCount(p.idItem, p.nameItem, COUNT(pu.idOrder)) " +
            "FROM item p " +
            "JOIN p.orders pu " +
            "GROUP BY p.idItem, p.nameItem " +
            "ORDER BY COUNT(pu.idOrder) DESC")
    List<ItemWithCount> findTop5Items();

}
