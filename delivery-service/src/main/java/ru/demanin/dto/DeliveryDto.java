package ru.demanin.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeliveryDto {

    private int orderId;

    private RestaurantForDeliveryDTO restaurant;

    private CustomerForDeliveryDTO customer;

}
