package ru.demanin.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItemsDTO {

    private long price;

    private int quantity;

    private String description;

    private String image;
}
