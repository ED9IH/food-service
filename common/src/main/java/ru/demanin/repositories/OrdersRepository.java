package ru.demanin.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.demanin.entity.Order;

@Repository
public interface OrdersRepository extends JpaRepository<Order,Long>{

}
