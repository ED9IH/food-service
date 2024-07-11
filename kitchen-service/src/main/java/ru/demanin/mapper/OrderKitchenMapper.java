package ru.demanin.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.demanin.dto.OrderDTO;
import ru.demanin.entity.Order;

import java.util.List;

@Mapper(componentModel = "spring", uses = {OrderItemMapper.class})
public interface OrderKitchenMapper {

    List<OrderDTO> toOrderDTO(List<Order> orders);

    @Mapping(source = "orderItems", target = "menu_items")
    OrderDTO toDto(Order order);

}
