package ru.demanin.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.demanin.entity.Order;
import ru.demanin.mapper.OrderMapper;
import ru.demanin.repositories.OrdersRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrdersService {

    private final OrdersRepository ordersRepository;

    private final OrderMapper orderMapper;


    public List<Order> finAll() {
        return ordersRepository.findAll();
    }
}
