package ru.demanin.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.demanin.dto.RestaurantMenuItemsDTO;
import ru.demanin.entity.RestaurantMenuItems;

@Mapper(componentModel = "spring")
public interface RestaurantMenuItemsMapper {
    RestaurantMenuItemsDTO toDTO(RestaurantMenuItems restaurantMenuItems);

    RestaurantMenuItems toEntity(RestaurantMenuItemsDTO restaurantMenuItemsDTO);
}
