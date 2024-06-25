package ru.uvarov.ystu.kursach.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import ru.uvarov.ystu.kursach.model.Seller;

public interface SellerRepository extends JpaRepository<Seller, Long>, PagingAndSortingRepository<Seller, Long> {
}
