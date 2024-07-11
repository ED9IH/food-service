package ru.demanin.dto;

import lombok.Getter;
import lombok.Setter;
import ru.demanin.entity.RestaurantMenuItems;

@Getter
@Setter
public class OrderItemDTO {

    private int quantity;

    private long menu_item_id;

}
