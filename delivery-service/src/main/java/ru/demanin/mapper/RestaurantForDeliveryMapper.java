package ru.demanin.mapper;

import org.mapstruct.Mapper;
import ru.demanin.dto.RestaurantForDeliveryDTO;
import ru.demanin.entity.Restaurant;

@Mapper(componentModel = "spring")
public interface RestaurantForDeliveryMapper {

    RestaurantForDeliveryDTO toDto(Restaurant restaurant);

    Restaurant toEntity(RestaurantForDeliveryDTO restaurantForDeliveryDTO);
}
