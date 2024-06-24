package ru.demanin.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateOrdersDTO {

    private long restaurantId;

    private int quantity;

    private RestaurantMenuItemsDTO restaurantMenuItemsDTO;









}
