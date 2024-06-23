package ru.demanin.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.demanin.dto.OrderDTO;
import ru.demanin.dto.RestaurantDTO;
import ru.demanin.entity.Order;
import ru.demanin.service.OrdersService;

import java.util.List;

@RestController
@RequestMapping
public class OrderController {
    public final OrdersService ordersService;

    @Autowired
    public OrderController(OrdersService ordersService) {
        this.ordersService = ordersService;
    }

    @GetMapping("/orders")
    public List<OrderDTO> getOrder() {
        return ordersService.getAllOrder();
    }
    @GetMapping("/orders/{id}")
    public OrderDTO getByOrderId (@PathVariable long id){
        return ordersService.getOrderById(id);
    }
}
