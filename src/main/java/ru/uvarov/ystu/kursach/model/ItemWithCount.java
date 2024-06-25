package ru.uvarov.ystu.kursach.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ItemWithCount {

    private Long idItem;

    private String nameItem;

    private Long countInOrder;

}
