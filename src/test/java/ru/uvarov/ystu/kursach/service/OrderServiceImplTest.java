package ru.uvarov.ystu.kursach.service;

import ru.uvarov.ystu.kursach.exceptions.ExceptionHandler;
import ru.uvarov.ystu.kursach.model.Buyer;
import ru.uvarov.ystu.kursach.model.Seller;
import ru.uvarov.ystu.kursach.model.Item;
import ru.uvarov.ystu.kursach.model.Order;
import ru.uvarov.ystu.kursach.repository.BuyerRepository;
import ru.uvarov.ystu.kursach.repository.SellerRepository;
import ru.uvarov.ystu.kursach.repository.ItemRepository;
import ru.uvarov.ystu.kursach.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class OrderServiceImplTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private ItemRepository itemRepository;

    @Mock
    private BuyerRepository buyerRepository;

    @Mock
    private SellerRepository sellerRepository;

    @Mock
    private ExceptionHandler exceptionHandler;

    @InjectMocks
    private OrderServiceImpl orderService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addOrder_success() {
        Order order = new Order();
        Item item = new Item();
        Buyer buyer = new Buyer();
        Seller seller = new Seller();

        order.setItem(item);
        order.setBuyer(buyer);
        order.setSeller(seller);

        when(itemRepository.findById(any())).thenReturn(Optional.of(item));
        when(buyerRepository.findById(any())).thenReturn(Optional.of(buyer));
        when(sellerRepository.findById(any())).thenReturn(Optional.of(seller));

        orderService.addOrder(order);

        verify(orderRepository, times(1)).save(order);
    }


    @Test
    void deleteOrderById_success() {
        Long orderId = 1L;

        orderService.deleteOrderById(orderId);

        verify(orderRepository, times(1)).deleteById(orderId);
    }
}
