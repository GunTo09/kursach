package ru.uvarov.ystu.kursach.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.uvarov.ystu.kursach.model.Item;
import ru.uvarov.ystu.kursach.model.ItemWithCount;
import ru.uvarov.ystu.kursach.repository.ItemRepository;
import ru.uvarov.ystu.kursach.service.ItemService;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/item")
public class ItemController {

    private final ItemRepository itemRepository;
    private final ItemService itemService;

    @PostMapping
    ResponseEntity<Void> addItem(@RequestBody @Valid Item item,
                                  BindingResult bindingResult){
        itemService.addItem(item);
        return ResponseEntity.ok().build();

    }


    @GetMapping
    ResponseEntity<Page<Item>> getAllItem(@RequestParam int page){
        return ResponseEntity.ok(itemService.getAllItem(page));

    }


    @GetMapping("/{id}")
    ResponseEntity<Item> getItemById(@PathVariable Long id){
        Optional<Item> itemOptional = itemService.getItemById(id);
        return itemOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }


    @PutMapping("/{id}")
    ResponseEntity<Item> updateItemById(@PathVariable Long id, @RequestBody Item newItem) {
        Optional<Item> newItemOptional = itemService.putItemById(id, newItem);
        return newItemOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteItemById(@PathVariable Long id){
        itemService.deleteItemById(id);
        return ResponseEntity.ok().build();
    }

    //популярные товары топ 5
    @GetMapping("/top5")
    ResponseEntity<List<ItemWithCount>> getTop5Items() {
        return ResponseEntity.ok(itemRepository.findTop5Items());
    }

}
