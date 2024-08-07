package ru.demanin.mapper;

import org.mapstruct.*;
import ru.demanin.dto.CreateOrdersDTO;
import ru.demanin.dto.OrderDTO;
import ru.demanin.entity.Order;
import java.util.List;

@Mapper(componentModel = "spring", uses = {OrderItemMapper.class})
public interface OrderMapper {

    List<OrderDTO> toDtoAll(List<Order> orders);

    @Mapping(source = "orderItems", target = "items")
    @Mapping(source = "restaurants", target = "restaurant")
    OrderDTO toDto(Order order);

    @Mapping(source = "items", target = "orderItems")
    @Mapping(source = "restaurant", target = "restaurants")
    Order toEntity(OrderDTO orderDTO);

}
