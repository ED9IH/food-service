package ru.demanin.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.demanin.dto.DeliveryDto;
import ru.demanin.entity.Order;

import java.util.List;

@Mapper(componentModel = "spring",uses = {RestaurantForDeliveryMapper.class,CustomerForDeliveryMapper.class})
public interface DeliveryMapper {


    List<DeliveryDto> toDto(List<Order> orders);
    @Mapping(source = "order.id",target = "orderId")
    @Mapping(source = "restaurants.address",target = "restaurant.address")
    @Mapping(source = "restaurants.coordinates",target = "restaurant.distance")
    @Mapping(source = "customers.address",target = "customer.address")
    @Mapping(source = "customers.coordinates",target = "customer.distance")
    DeliveryDto toDto(Order order);
    Order toOrder(DeliveryDto deliveryDto);


}
