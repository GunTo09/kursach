package ru.uvarov.ystu.kursach.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.uvarov.ystu.kursach.exceptions.ExceptionHandler;
import ru.uvarov.ystu.kursach.model.Buyer;
import ru.uvarov.ystu.kursach.model.Item;
import ru.uvarov.ystu.kursach.model.Order;
import ru.uvarov.ystu.kursach.model.Seller;
import ru.uvarov.ystu.kursach.repository.BuyerRepository;
import ru.uvarov.ystu.kursach.repository.ItemRepository;
import ru.uvarov.ystu.kursach.repository.OrderRepository;
import ru.uvarov.ystu.kursach.repository.SellerRepository;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrderService{

    private final OrderRepository orderRepository;
    private final ItemRepository itemRepository;
    private final BuyerRepository buyerRepository;
    private final SellerRepository sellerRepository;
    private final ExceptionHandler exceptionHandler;

    @Override
    public void addOrder(Order order) {
        Item item = order.getItem();
        Buyer buyer = order.getBuyer();
        Seller seller = order.getSeller();

        if (item != null && item.getIdItem() != null) {
            item = itemRepository.findById(item.getIdItem()).orElse(null);
        }
        if (buyer != null && buyer.getIdBuyer() != null) {
            buyer = buyerRepository.findById(buyer.getIdBuyer()).orElse(null);
        }
        if (seller != null && seller.getIdSeller() != null) {
            seller = sellerRepository.findById(seller.getIdSeller()).orElse(null);
        }

        order.setItem(item);
        order.setBuyer(buyer);
        order.setSeller(seller);

        orderRepository.save(order);

    }

    @Override
    public Page<Order> getAllOrder(int page) {
        int size = 3;
        return orderRepository.findAll(PageRequest.of(page, size));
    }

    @Override
    public Optional<Order> getOrderById(Long id) {
        return orderRepository.findById(id);
    }

    @Override
    public Optional<Order> putOrderById(Long id, Order newOrder) {
        Optional<Order> existingOrder = orderRepository.findById(id);
        if (existingOrder.isPresent()){
            Order orderToUpdate = existingOrder.get();

            if(newOrder.getItem() != null && newOrder.getItem().getIdItem() != null) {
                Item item = itemRepository.findById(newOrder.getItem().getIdItem()).orElse(null);
                orderToUpdate.setItem(item);
            }

            if(newOrder.getCountItemInOrder() != null) {
                orderToUpdate.setCountItemInOrder(newOrder.getCountItemInOrder());
            }

            if(newOrder.getBuyer() != null && newOrder.getBuyer().getIdBuyer() != null) {
                Buyer buyer = buyerRepository.findById(newOrder.getBuyer().getIdBuyer()).orElse(null);
                orderToUpdate.setBuyer(buyer);
            }

            if(newOrder.getSeller() != null && newOrder.getSeller().getIdSeller() != null) {
                Seller seller = sellerRepository.findById(newOrder.getSeller().getIdSeller()).orElse(null);
                orderToUpdate.setSeller(seller);
            }
            orderRepository.save(orderToUpdate);
        }
        return existingOrder;
    }

    @Override
    public void deleteOrderById(Long id) {
        orderRepository.deleteById(id);
    }
}
