package ru.demanin.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.demanin.dto.DeliveryDto;
import ru.demanin.dto.OrderDTO;
import ru.demanin.entity.Order;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DeliveryMapper {

    @Mapping(source = "id",target = "orderId")
    List<DeliveryDto> toDto(List<Order> orders);


}
