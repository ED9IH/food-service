package ru.demanin.mapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.demanin.dto.OrderDTO;
import ru.demanin.entity.Order;
import java.util.List;
@Mapper(componentModel = "spring",uses = {RestaurantMapper.class,OrderItemMapper.class})
public interface OrderMapper {

    List<OrderDTO> toDto(List<Order> orders);

    @Mapping(source = "restaurants",target = "restaurant")
    @Mapping(source = "restaurants.restaurantMenuItems",target = "items")
    OrderDTO toDto(Order order);
    @Mapping(source = "restaurant",target = "restaurants")
    @Mapping(source = "items",target = "restaurants.restaurantMenuItems")
    Order toEntity(OrderDTO orderDTO);





}
