package ru.uvarov.ystu.kursach.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import ru.uvarov.ystu.kursach.exceptions.ExceptionHandler;
import ru.uvarov.ystu.kursach.model.Seller;
import ru.uvarov.ystu.kursach.repository.SellerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SellerServiceImplTest {

    @Mock
    private SellerRepository sellerRepository;

    @Mock
    private ExceptionHandler exceptionHandler;

    @InjectMocks
    private SellerServiceImpl sellerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addSeller() {
        Seller seller = new Seller();
        seller.setIdSeller(1L);
        seller.setNameSeller("AAA");
        seller.setSurnameSeller("UUU");
        seller.setNumTelephoneSeller("123456789");

        // Mock the save method
        when(sellerRepository.save(seller)).thenReturn(seller);

        sellerService.addSeller(seller);

        verify(sellerRepository, times(1)).save(seller);
    }

    @Test
    void getAllSeller() {
        Seller seller1 = new Seller();
        seller1.setIdSeller(1L);
        seller1.setNameSeller("AAA");
        seller1.setSurnameSeller("UUU");

        Seller seller2 = new Seller();
        seller2.setIdSeller(2L);
        seller2.setNameSeller("BBB");
        seller2.setSurnameSeller("SSS");

        Page<Seller> page = new PageImpl<>(Arrays.asList(seller2, seller1));

        when(sellerRepository.findAll(PageRequest.of(0, 4))).thenReturn(page);

        Page<Seller> result = sellerService.getAllSeller(0);

        assertNotNull(result);
        assertEquals(2, result.getTotalElements());
        verify(sellerRepository, times(1)).findAll(PageRequest.of(0, 4));
    }

    @Test
    void getSellerById() {
        Seller seller = new Seller();
        seller.setIdSeller(1L);
        seller.setNameSeller("AAA");
        seller.setSurnameSeller("UUU");

        when(sellerRepository.findById(1L)).thenReturn(Optional.of(seller));

        Optional<Seller> result = sellerService.getSellerById(1L);

        assertTrue(result.isPresent());
        assertEquals("AAA", result.get().getNameSeller());
        verify(sellerRepository, times(1)).findById(1L);
    }

    @Test
    void putSellerById() {
        Seller existingSeller = new Seller();
        existingSeller.setIdSeller(1L);
        existingSeller.setNameSeller("AAA");
        existingSeller.setSurnameSeller("DDD");

        Seller updatedSeller = new Seller();
        updatedSeller.setNameSeller("BBB");
        updatedSeller.setSurnameSeller("SSS");
        updatedSeller.setNumTelephoneSeller("987654321");

        when(sellerRepository.findById(1L)).thenReturn(Optional.of(existingSeller));
        when(sellerRepository.save(existingSeller)).thenReturn(existingSeller);

        Optional<Seller> result = sellerService.putSellerById(1L, updatedSeller);

        assertTrue(result.isPresent());
        assertEquals("BBB", result.get().getNameSeller());
        assertEquals("SSS", result.get().getSurnameSeller());
        assertEquals("987654321", result.get().getNumTelephoneSeller());
        verify(sellerRepository, times(1)).findById(1L);
        verify(sellerRepository, times(1)).save(existingSeller);
    }

    @Test
    void deleteSellerById() {
        // Mock the deleteById method
        doNothing().when(sellerRepository).deleteById(1L);

        sellerService.deleteSellerById(1L);

        verify(sellerRepository, times(1)).deleteById(1L);
    }
}
