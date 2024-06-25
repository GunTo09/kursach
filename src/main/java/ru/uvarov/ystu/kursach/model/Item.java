package ru.uvarov.ystu.kursach.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.util.List;
import java.util.Optional;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "item")
@Table(name = "item")
public class Item {

    @Id
    @Column(name = "id_item")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idItem;

    @NotEmpty(message = "Sorry, empty")
    @Column(name = "name_item")
    private String nameItem;

    @NotEmpty(message = "Sorry, empty")
    @Column(name = "available_quantity")
    private Long availableQuantity;

    @NotEmpty(message = "Sorry, empty")
    @Column(name = "price")
    private Long price;

    @NotEmpty(message = "Sorry, empty")
    @Column(name = "discount")
    private Long discount;

    @JsonIgnore
    @OneToMany(mappedBy = "item")
    private List<Order> orders;

}
