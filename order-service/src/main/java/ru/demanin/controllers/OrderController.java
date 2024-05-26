package ru.demanin.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
    public List<Order> getOrder() {
        return ordersService.finAll();
    }
}
