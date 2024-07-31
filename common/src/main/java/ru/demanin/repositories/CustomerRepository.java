package ru.demanin.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.demanin.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
