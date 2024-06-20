package ru.demanin.mapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.demanin.dto.OrderDTO;
import ru.demanin.entity.Order;
import java.util.List;
@Mapper(componentModel = "spring",uses = {RestaurantMapper.class,OrderItemMapper.class})
public interface OrderMapper {

    List<OrderDTO> toDto(List<Order> orders);

    @Mapping(source = "restaurants",target = "restaurantDTO")
    OrderDTO toDto(Order order);
    @Mapping(source = "restaurantDTO",target = "restaurants")
    Order toEntity(OrderDTO orderDTO);





}
