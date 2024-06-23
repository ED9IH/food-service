package ru.demanin.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
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
    private final OrderMapper orderMapper;



    public List<OrderDTO> getAllOrder(){
        return orderMapper.toDto(ordersRepository.findAll());
    }
    public OrderDTO getOrderById(long id){
        return orderMapper.toDto(ordersRepository.getById(id));
    }




}
