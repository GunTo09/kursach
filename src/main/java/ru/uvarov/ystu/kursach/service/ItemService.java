package ru.uvarov.ystu.kursach.service;

import org.springframework.data.domain.Page;
import ru.uvarov.ystu.kursach.model.Item;

import java.util.Optional;

public interface ItemService {

    void addItem (Item item);

    Page<Item> getAllItem(int page);

    Optional<Item> getItemById(Long id);

    Optional<Item> putItemById(Long id, Item newItem);

    void deleteItemById (Long id);

}
