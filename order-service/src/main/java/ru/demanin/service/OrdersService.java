package ru.demanin.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.demanin.dto.OrderDTO;
import ru.demanin.dto.RestaurantDTO;
import ru.demanin.entity.Order;
import ru.demanin.entity.Restaurant;
import ru.demanin.mapper.OrderMapper;
import ru.demanin.mapper.RestaurantMapper;
import ru.demanin.repositories.OrdersRepository;
import org.springframework.transaction.annotation.Transactional;
import ru.demanin.repositories.RestaurantRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrdersService {
    @Autowired
    private final OrdersRepository ordersRepository;
    @Autowired
    private final RestaurantRepository restaurantRepository;
    @Autowired
    private final OrderMapper orderMapper;
    @Autowired
    private final RestaurantMapper restaurantMapper;


    public List<OrderDTO> getAllOrder(){
        List<Order> orders= ordersRepository.findAll();
        return orderMapper.toDtoOrder(orders);
    }

    public List<RestaurantDTO> getAll(){
        List<Restaurant> restaurant = restaurantRepository.findAll();
        return restaurantMapper.getAll(restaurant);
    }




}
