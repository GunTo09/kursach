package ru.uvarov.ystu.kursach.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "order")
@Entity(name = "order")
public class Order {

    @Id
    @Column(name = "id_order")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idOrder;

    @ManyToOne
    @JoinColumn(name = "id_item")
    private Item item;

    @NotEmpty(message = "Sorry, empty")
    @Column(name="count_item_in_order")
    private Long countItemInOrder;

    @NotEmpty(message = "Sorry, empty")
    @ManyToOne
    @JoinColumn(name = "id_buyer")
    private Buyer buyer;

    @NotEmpty(message = "Sorry, empty")
    @ManyToOne
    @JoinColumn(name = "id_seller")
    private Seller seller;

    @NotEmpty(message = "Sorry, empty")
    @CreationTimestamp
    @Column(name = "date_added_order")
    private LocalDateTime dateAddedOrder;

}
