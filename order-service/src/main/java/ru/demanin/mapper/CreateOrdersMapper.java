package ru.demanin.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.demanin.dto.CreateOrdersDTO;
import ru.demanin.entity.Order;

@Mapper(componentModel = "spring", uses = {RestaurantMenuItemsMapper.class,OrderItemMapper.class})
public interface CreateOrdersMapper {
    @Mapping(source = "restaurants.id",target = "restaurantId")
    //@Mapping(source = "restaurants.restaurantMenuItems.",target = "")
    CreateOrdersDTO toDto(Order order);

    @Mapping(source = "restaurantId",target = "restaurants.id")
   // @Mapping(source = "",target = "")
    Order toEntity(CreateOrdersDTO createOrdersDTO);
}
