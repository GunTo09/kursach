package ru.uvarov.ystu.kursach.service;


import org.springframework.data.domain.Page;
import ru.uvarov.ystu.kursach.model.Buyer;

import java.util.Optional;

public interface BuyerService {
    void addBuyer(Buyer buyer);

    Page<Buyer> getAllBuyers(int page);

    Optional<Buyer> getBuyerById(Long id);

    Optional<Buyer> putBuyerById(Long id, Buyer updatedBuyer);

    void deleteBuyerById(Long id);

}
