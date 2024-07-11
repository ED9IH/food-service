package ru.demanin.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.demanin.entity.OrderItems;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItems,Long> {
}
