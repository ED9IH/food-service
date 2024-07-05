package ru.demanin.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import ru.demanin.dto.CreateOrdersDTO;
import ru.demanin.dto.DeliveryDto;
import ru.demanin.dto.OrderDTO;
import ru.demanin.dto.RestaurantDTO;
import ru.demanin.entity.Order;
import ru.demanin.entity.Restaurant;
import ru.demanin.mapper.CreateOrdersMapper;
import ru.demanin.mapper.DeliveryMapper;
import ru.demanin.mapper.OrderMapper;
import ru.demanin.mapper.RestaurantMapper;
import ru.demanin.repositories.OrdersRepository;
import org.springframework.transaction.annotation.Transactional;
import ru.demanin.repositories.RestaurantRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrdersService {
    @Autowired
    private final OrdersRepository ordersRepository;
    @Autowired
    private final OrderMapper orderMapper;
    @Autowired
    private final CreateOrdersMapper createOrdersMapper;
    @Autowired
    private DeliveryMapper deliveryMapper;



//    Customer REST API
//    GET /orders
    public List<OrderDTO> getAllOrder() {
        return orderMapper.toDto(ordersRepository.findAll());
    }


    //    GET /order/${id}
    public OrderDTO getOrderById(long id) {
        return orderMapper.toDto(ordersRepository.getById(id));
    }
//    POST /order
    @Transactional
    public Order save(CreateOrdersDTO createOrdersDTO) {
        return ordersRepository.save(createOrdersMapper.toEntity(createOrdersDTO));
    }
    //Для тестирования
    public List<DeliveryDto> getAllDelivery(String status) {
        return deliveryMapper.toDto(ordersRepository.findAll()
                .stream().filter(order -> order.getStatus().equals(status))
                .collect(Collectors.toList()));
    }
    @Transactional
    public OrderDTO updateStatus(long id) {
        Order order = ordersRepository.getById(id);
        if (order != (null)) {
            order.setStatus(order.getStatus());
        }
        return orderMapper.toDto(ordersRepository.save(order));
    }


}
