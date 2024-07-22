package ru.demanin.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.demanin.dto.OrderDTO;
import ru.demanin.entity.Order;
import java.util.List;

@Mapper(componentModel = "spring", uses = {OrderItemMapper.class})
public interface OrderMapper {

    List<OrderDTO> toDtoAll(List<Order> orders);

    @Mapping(source = "orderItems", target = "items")
    @Mapping(source = "restaurants", target = "restaurant")
    OrderDTO toDto(Order order);

}
