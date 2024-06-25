package ru.uvarov.ystu.kursach.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.uvarov.ystu.kursach.model.Order;
import ru.uvarov.ystu.kursach.repository.OrderRepository;
import ru.uvarov.ystu.kursach.service.OrderService;

import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;
    private final OrderRepository orderRepository;

    @PostMapping
    ResponseEntity<Void> addOrder(@RequestBody @Valid Order order,
                                  BindingResult bindingResult){
        orderService.addOrder(order);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    ResponseEntity<Page<Order>> getAllOrder(@RequestParam int page){
        return ResponseEntity.ok(orderService.getAllOrder(page));
    }

    @GetMapping("/{id}")
    ResponseEntity<Order> getOrderById(@PathVariable Long id){
        Optional<Order> orderOptional = orderService.getOrderById(id);
        return orderOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    ResponseEntity<Order> updateOrderById(@PathVariable Long id, @RequestBody @Validated Order newOrder) {
        Optional<Order> newOrderOptional = orderService.putOrderById(id, newOrder);
        return newOrderOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteOrderById(@PathVariable Long id){
        orderService.deleteOrderById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/count")
    ResponseEntity<Long> countOrdersBetweenDates(@RequestParam LocalDateTime startDate, @RequestParam LocalDateTime endDate) {
        Long count = orderRepository.countOrdersBetweenDates(startDate, endDate);
        return ResponseEntity.ok(count);
    }

    @GetMapping("/revenue")
    ResponseEntity<Long> totalRevenueBetweenDates(@RequestParam LocalDateTime startDate, @RequestParam LocalDateTime endDate) {
        Long revenue = orderRepository.totalRevenueBetweenDates(startDate, endDate);
        return ResponseEntity.ok(revenue);
    }
}
