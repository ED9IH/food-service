package ru.demanin.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.demanin.dto.CreateOrdersDTO;
import ru.demanin.dto.OrderItemsDTO;
import ru.demanin.entity.Order;
import ru.demanin.entity.OrderItems;

@Mapper(componentModel = "spring", uses = {RestaurantMenuItemsMapper.class})
public interface CreateOrdersMapper {

    @Mapping(source = "restaurantMenuItems.id",target = "restaurantMenuItemsDTO.id")
    @Mapping(source = "quantity",target = "restaurantMenuItemsDTO.quantity")
    CreateOrdersDTO toDto(OrderItems orderItems);

    @Mapping(source = "restaurantMenuItemsDTO.id",target = "restaurantMenuItems.id")
    @Mapping(source = "restaurantMenuItemsDTO.quantity",target = "quantity")
    OrderItems toEntity(CreateOrdersDTO createOrdersDTO);
}
