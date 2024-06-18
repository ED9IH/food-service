package ru.demanin.mapper;
import org.mapstruct.Mapper;
import ru.demanin.dto.RestaurantDTO;
import ru.demanin.entity.Restaurant;
@Mapper(componentModel = "spring")
public interface RestaurantMapper {
    RestaurantDTO restaurantToRestaurantDto(Restaurant restaurant);
}
