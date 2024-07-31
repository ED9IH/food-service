package ru.demanin.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.demanin.entity.RestaurantMenuItems;

public interface RestaurantMenuItemsRepository extends JpaRepository<RestaurantMenuItems,Long > {
}
