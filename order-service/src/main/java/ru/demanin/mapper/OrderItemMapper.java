package ru.demanin.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.demanin.dto.OrderItemsDTO;
import ru.demanin.entity.OrderItems;
import ru.demanin.entity.RestaurantMenuItems;

@Mapper(componentModel = "spring")
public interface OrderItemMapper {
    @Mapping(source = "orderItems.restaurantMenuItems.image", target = "image")
    @Mapping(source = "orderItems.restaurantMenuItems.description", target = "description")
    OrderItemsDTO toDto(OrderItems orderItems);

}
