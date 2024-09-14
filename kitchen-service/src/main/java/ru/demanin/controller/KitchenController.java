package ru.demanin.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.demanin.dto.OrderDTO;
import ru.demanin.response.KitchenResponse;
import ru.demanin.response.RestaurantResponse;
import ru.demanin.service.KitchenService;
import ru.demanin.status.OrderStatus;
import ru.demanin.status.RestaurantStatus;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class KitchenController {
    @Autowired
    private KitchenService kitchenService;

    @GetMapping("/new")
    public ResponseEntity<List<OrderDTO>> getOrders() {
        return ResponseEntity.ok(kitchenService.getOrders());
    }

    @PostMapping("/{id}/active")
    public ResponseEntity<KitchenResponse> updateOrdersActive(@PathVariable long id) throws JsonProcessingException {
        kitchenService.updateStatusOrderActive(id);
        return ResponseEntity.ok(new KitchenResponse(id, OrderStatus.ORDER_ACTIVE));
    }

    @PostMapping("/{id}/denied")
    public ResponseEntity<KitchenResponse> updateOrdersDenied(@PathVariable long id) throws JsonProcessingException {
        kitchenService.updateStatusOrderDenied(id);
        return ResponseEntity.ok(new KitchenResponse(id, OrderStatus.ORDER_DENIED));
    }

    @PostMapping("/{id}/completed")
    public ResponseEntity<KitchenResponse> updateStatusOrderСompleted(@PathVariable long id) throws JsonProcessingException {
        kitchenService.updateStatusOrderСompleted(id);
        return ResponseEntity.ok(new KitchenResponse(id, OrderStatus.ORDER_COMPLETE));
    }

    @PostMapping("/{id}/open")
    public ResponseEntity<RestaurantResponse> openRestaurant(@PathVariable long id) throws JsonProcessingException {
        kitchenService.openRestaurant(id);
        return ResponseEntity.ok(new RestaurantResponse(id, RestaurantStatus.OPEN));
    }

    @PostMapping("/{id}/close")
    public ResponseEntity<RestaurantResponse> updateOrdersComplete(@PathVariable long id) throws JsonProcessingException {
        kitchenService.closeRestaurant(id);
        return ResponseEntity.ok(new RestaurantResponse(id, RestaurantStatus.CLOSED));
    }

}
