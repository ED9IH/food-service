package ru.demanin.mapper;

import org.mapstruct.Mapper;
import ru.demanin.dto.OrderItemsDTO;
import ru.demanin.entity.OrderItems;
import ru.demanin.entity.RestaurantMenuItems;

@Mapper(componentModel = "spring")
public interface OrderItemMapper {
    OrderItemsDTO toDto(OrderItems orderItems);

    OrderItems toEntity(OrderItemsDTO orderItemsDTO);

}
