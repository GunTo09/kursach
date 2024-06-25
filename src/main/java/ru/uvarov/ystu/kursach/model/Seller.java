package ru.uvarov.ystu.kursach.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "seller")
@Entity(name = "seller")
public class Seller {

    @Id
    @Column(name="id_seller")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSeller;

    @NotEmpty(message = "Sorry, empty")
    @Column(name = "name_seller")
    private String nameSeller;

    @NotEmpty(message = "Sorry, empty")
    @Column(name = "surname_seller")
    private String surnameSeller;


    @NotEmpty(message = "Sorry, empty")
    @Column(name = "number_telephone_seller")
    private String numTelephoneSeller;

    @JsonIgnore
    @OneToMany(mappedBy = "seller")
    private List<Order> orders;

}
