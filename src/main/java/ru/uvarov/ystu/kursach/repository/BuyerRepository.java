package ru.uvarov.ystu.kursach.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import ru.uvarov.ystu.kursach.model.Buyer;

public interface BuyerRepository extends JpaRepository<Buyer, Long> , PagingAndSortingRepository<Buyer, Long> {

}
