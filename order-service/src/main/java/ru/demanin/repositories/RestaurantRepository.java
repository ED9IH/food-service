package ru.demanin.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.demanin.entity.Restaurant;

public interface RestaurantRepository extends JpaRepository<Restaurant,Long> {
}
