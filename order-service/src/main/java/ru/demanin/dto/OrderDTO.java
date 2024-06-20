package ru.demanin.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import ru.demanin.entity.Restaurant;

import java.util.List;

@Getter
@Setter
public class OrderDTO {

    private long id;

    private RestaurantDTO restaurantDTO;

    private List<OrderItemsDTO> items;

}
