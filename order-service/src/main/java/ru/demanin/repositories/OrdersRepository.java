package ru.demanin.repositories;


import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.demanin.entity.Order;

import java.util.List;

@Repository
public interface OrdersRepository extends JpaRepository<Order,Long>{

}
