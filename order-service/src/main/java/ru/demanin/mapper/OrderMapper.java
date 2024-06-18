package ru.demanin.mapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.demanin.dto.OrderDTO;
import ru.demanin.entity.Order;
import ru.demanin.entity.Restaurant;
import java.util.List;
@Mapper(componentModel = "spring", uses = {RestaurantMapper.class, OrderItemMapper.class})
public interface OrderMapper {
    @Mapping(source = "orderItems", target = "items")
    OrderDTO orderToOrderDTO(Order order);

    @Mapping(source = "orderItems", target = "items")
    List<OrderDTO> toDtoList(List<Order> orders);

    default Restaurant mapRestaurantIdToRestaurant(Long restaurantId) {
        Restaurant restaurant = new Restaurant();
        restaurant.setId(restaurantId);
        return restaurant;
    }
}
