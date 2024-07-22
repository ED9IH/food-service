package ru.demanin.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.demanin.entity.Restaurant;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant,Long> {
}
