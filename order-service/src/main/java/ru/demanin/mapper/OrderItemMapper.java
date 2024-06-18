package ru.demanin.mapper;
import org.mapstruct.Mapper;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import ru.demanin.dto.OrderItemsDTO;
import ru.demanin.entity.OrderItems;
import ru.demanin.entity.RestaurantMenuItems;
@ComponentScan
@Mapper(componentModel = "spring")
public interface OrderItemMapper {

    OrderItemsDTO toDto(OrderItems orderItems);


    default RestaurantMenuItems mapRestaurantMenuItemIdToRestaurant(Long restaurantMenuItemId) {
        RestaurantMenuItems restaurantMenuItems = new RestaurantMenuItems();
        restaurantMenuItems.setId(restaurantMenuItemId);
        return restaurantMenuItems;
    }
}