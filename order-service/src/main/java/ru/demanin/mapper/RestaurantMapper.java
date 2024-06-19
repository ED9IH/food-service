package ru.demanin.mapper;

import org.mapstruct.Mapper;
import ru.demanin.dto.RestaurantDTO;
import ru.demanin.entity.Restaurant;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RestaurantMapper {
    RestaurantDTO restaurantToRestaurantDto(Restaurant restaurant);

    List<RestaurantDTO> getAll(List<Restaurant> restaurants);
}
