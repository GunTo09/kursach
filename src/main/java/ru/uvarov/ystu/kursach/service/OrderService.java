package ru.uvarov.ystu.kursach.service;

import org.springframework.data.domain.Page;
import ru.uvarov.ystu.kursach.model.Order;

import java.util.Optional;

public interface OrderService {

    void addOrder(Order order);

    Page<Order> getAllOrder(int page);

    Optional<Order> getOrderById (Long id);

    Optional<Order> putOrderById (Long id, Order newOrder);

    void deleteOrderById (Long id);

}
