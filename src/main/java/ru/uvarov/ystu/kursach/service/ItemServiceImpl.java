package ru.uvarov.ystu.kursach.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.uvarov.ystu.kursach.exceptions.ExceptionHandler;
import ru.uvarov.ystu.kursach.model.Item;
import ru.uvarov.ystu.kursach.repository.ItemRepository;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ItemServiceImpl implements ItemService{

    private final ItemRepository itemRepository;

    private final ExceptionHandler exceptionHandler;

    @Override
    public void addItem(Item item) {
        itemRepository.save(item);
    }

    @Override
    public Page<Item> getAllItem(int page) {
        int size = 5;
        return itemRepository.findAll(PageRequest.of(page, size));
    }

    @Override
    public Optional<Item> getItemById(Long id) {
        return itemRepository.findById(id);
    }

    @Override
    public Optional<Item> putItemById(Long id, Item newItem) {
        Optional<Item> existingItem = itemRepository.findById(id);
        if (existingItem.isPresent()){
            Item itemToUpdate = existingItem.get();
            if (newItem.getNameItem() != null){
                itemToUpdate.setNameItem(newItem.getNameItem());
            }
            if (newItem.getDiscount() != null){
                itemToUpdate.setDiscount(newItem.getDiscount());
            }
            if (newItem.getPrice() != null){
                itemToUpdate.setPrice(newItem.getPrice());
            }
            if (newItem.getAvailableQuantity() != null){
                itemToUpdate.setAvailableQuantity(newItem.getAvailableQuantity());
            }
            itemRepository.save(itemToUpdate);
        }
        return existingItem;
    }

    @Override
    public void deleteItemById(Long id) {
        itemRepository.deleteById(id);
    }
}
