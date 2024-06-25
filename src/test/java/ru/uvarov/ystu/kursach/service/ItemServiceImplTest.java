package ru.uvarov.ystu.kursach.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import ru.uvarov.ystu.kursach.exceptions.ExceptionHandler;
import ru.uvarov.ystu.kursach.model.Item;
import ru.uvarov.ystu.kursach.repository.ItemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ItemServiceImplTest {

    @Mock
    private ItemRepository itemRepository;

    @Mock
    private ExceptionHandler exceptionHandler;

    @InjectMocks
    private ItemServiceImpl itemService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addItem() {
        Item item = new Item();
        item.setIdItem(1L);
        item.setNameItem("Test Product");
        item.setDiscount(10L);
        item.setPrice(100L);
        item.setAvailableQuantity(50L);

        when(itemRepository.save(item)).thenReturn(item);

        itemService.addItem(item);

        verify(itemRepository, times(1)).save(item);
    }


    @Test
    void getAllItem() {
        Item item1 = new Item();
        item1.setIdItem(1L);

        Item item2 = new Item();
        item2.setIdItem(2L);
        item2.setNameItem("BBB");

        Page<Item> page = new PageImpl<>(Arrays.asList(item1, item2));

        when(itemRepository.findAll(PageRequest.of(0, 4))).thenReturn(page);

        Page<Item> result = itemService.getAllItem(0);

        assertNotNull(result);
        assertEquals(2, result.getTotalElements());
        verify(itemRepository, times(1)).findAll(PageRequest.of(0, 4));
    }

    @Test
    void getItemById() {
        Item item = new Item();
        item.setIdItem(1L);
        item.setNameItem("AAA");

        when(itemRepository.findById(1L)).thenReturn(Optional.of(item));

        Optional<Item> result = itemService.getItemById(1L);

        assertTrue(result.isPresent());
        assertEquals("AAA", result.get().getNameItem());
        verify(itemRepository, times(1)).findById(1L);
    }

    @Test
    void putItemById() {
        Item existingItem = new Item();
        existingItem.setIdItem(1L);
        existingItem.setNameItem("AAA");
        existingItem.setDiscount(5L);
        existingItem.setPrice(100L);
        existingItem.setAvailableQuantity(50L);

        Item updatedItem = new Item();
        updatedItem.setNameItem("BBB");
        updatedItem.setDiscount(10L);
        updatedItem.setPrice(120L);
        updatedItem.setAvailableQuantity(60L);

        when(itemRepository.findById(1L)).thenReturn(Optional.of(existingItem));
        when(itemRepository.save(existingItem)).thenReturn(existingItem);

        Optional<Item> result = itemService.putItemById(1L, updatedItem);

        assertTrue(result.isPresent());
        assertEquals("BBB", result.get().getNameItem());
        assertEquals(10L, result.get().getDiscount());
        assertEquals(120L, result.get().getPrice());
        assertEquals(60L, result.get().getAvailableQuantity());
        verify(itemRepository, times(1)).findById(1L);
        verify(itemRepository, times(1)).save(existingItem);
    }

    @Test
    void deleteItemById() {
        doNothing().when(itemRepository).deleteById(1L);

        itemService.deleteItemById(1L);

        verify(itemRepository, times(1)).deleteById(1L);
    }
}
