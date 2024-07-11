package ru.demanin.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.demanin.dto.OrderItemDTO;
import ru.demanin.entity.OrderItems;

@Mapper(componentModel = "spring")
public interface OrderItemMapper {

    @Mapping(source = "restaurantMenuItems.id", target = "menu_item_id")
    OrderItemDTO toDto(OrderItems orderItems);
}
