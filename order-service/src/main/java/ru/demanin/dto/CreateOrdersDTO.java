package ru.demanin.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class CreateOrdersDTO {

    private long restaurant_id;//+

    private List<RestaurantMenuItemsDTO> menuItems;//+

}
