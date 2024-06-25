package ru.uvarov.ystu.kursach.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import ru.uvarov.ystu.kursach.repository.ItemRepository;
import ru.uvarov.ystu.kursach.service.ItemService;
import ru.uvarov.ystu.kursach.model.Item;
import ru.uvarov.ystu.kursach.model.ItemWithCount;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ItemControllerTest {

    @Mock
    private ItemService itemService;

    @Mock
    private ItemRepository itemRepository;

    @InjectMocks
    private ItemController itemController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void getAllItem_ReturnsOk() {
        Page<Item> item = null;
        when(itemService.getAllItem(0)).thenReturn(item);

        ResponseEntity<Page<Item>> response = itemController.getAllItem(0);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(item, response.getBody());
    }

    @Test
    void getItemById_ReturnsOk() {
        Long id = 1L;
        Item item = new Item();
        when(itemService.getItemById(id)).thenReturn(Optional.of(item));

        ResponseEntity<Item> response = itemController.getItemById(id);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(item, response.getBody());
    }

    @Test
    void getItemById_ReturnsNotFound() {
        Long id = 1L;
        when(itemService.getItemById(id)).thenReturn(Optional.empty());

        ResponseEntity<Item> response = itemController.getItemById(id);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void updateItemById_ReturnsOk() {
        Long id = 1L;
        Item newItem = new Item();
        when(itemService.putItemById(id, newItem)).thenReturn(Optional.of(newItem));

        ResponseEntity<Item> response = itemController.updateItemById(id, newItem);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(newItem, response.getBody());
    }

    @Test
    void updateItemById_ReturnsNotFound() {
        Long id = 1L;
        Item newItem = new Item();
        when(itemService.putItemById(id, newItem)).thenReturn(Optional.empty());

        ResponseEntity<Item> response = itemController.updateItemById(id, newItem);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void deleteItemById_ReturnsOk() {
        Long id = 1L;
        ResponseEntity<Void> response = itemController.deleteItemById(id);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(itemService, times(1)).deleteItemById(id);
    }

    @Test
    void getTop5Items_ReturnsOk() {
        List<ItemWithCount> top5Items = new ArrayList<>();
        when(itemRepository.findTop5Items()).thenReturn(top5Items);

        ResponseEntity<List<ItemWithCount>> response = itemController.getTop5Items();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(top5Items, response.getBody());
    }
}
