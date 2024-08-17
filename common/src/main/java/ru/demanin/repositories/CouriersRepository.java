package ru.demanin.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.demanin.entity.Couriers;

public interface CouriersRepository extends JpaRepository<Couriers,Long> {
}
