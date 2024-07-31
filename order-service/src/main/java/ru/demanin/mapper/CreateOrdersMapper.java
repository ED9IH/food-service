package ru.demanin.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.demanin.dto.CreateOrdersDTO;
import ru.demanin.entity.Order;
import ru.demanin.entity.RestaurantMenuItems;

@Mapper(componentModel = "spring", uses = {RestaurantMenuItemsMapper.class})
public interface CreateOrdersMapper {

    @Mapping(source = "restaurant_id", target = "restaurants.id")
    @Mapping(source = "menuItems", target = "orderItems")
    Order toOrder(CreateOrdersDTO createOrdersDTO);

}
