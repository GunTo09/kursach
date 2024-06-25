package ru.uvarov.ystu.kursach.service;

import org.springframework.data.domain.Page;
import ru.uvarov.ystu.kursach.model.Seller;

import java.util.List;
import java.util.Optional;

public interface SellerService {

    void addSeller(Seller seller);

    Page<Seller> getAllSeller(int page);

    Optional<Seller> getSellerById(Long id);

    Optional<Seller> putSellerById(Long id, Seller newSeller);

    void deleteSellerById(Long id);

}
