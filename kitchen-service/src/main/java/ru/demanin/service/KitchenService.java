package ru.demanin.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.demanin.dto.OrderDTO;
import ru.demanin.entity.Order;
import ru.demanin.mapper.OrderKitchenMapper;
import ru.demanin.repositories.OrdersRepository;
import ru.demanin.statusOrders.OrderStatus;


import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class KitchenService {

    @Autowired
    private final OrderKitchenMapper orderKitchenMapper;
    @Autowired
    private final OrdersRepository ordersRepository;

    public List<OrderDTO> getOrders() {
        return orderKitchenMapper.toOrderDTO(ordersRepository.findAll());

    }

    public Order acceptOrders(long id) {
        Order order = ordersRepository.getById(id);

        return ordersRepository.save(order);
    }

}
