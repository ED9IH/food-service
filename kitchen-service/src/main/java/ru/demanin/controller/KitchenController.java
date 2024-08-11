package ru.demanin.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.demanin.dto.OrderDTO;
import ru.demanin.response.KitchenResponse;
import ru.demanin.service.KitchenService;
import ru.demanin.statusOrders.OrderStatus;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class KitchenController {

    private final KitchenService kitchenService;


    @Autowired
    public KitchenController(KitchenService kitchenService) {
        this.kitchenService = kitchenService;

    }
    @GetMapping("/new")
    public ResponseEntity<List<OrderDTO>> getOrders() {
        return  ResponseEntity.ok(kitchenService.getOrders());
    }
    @PostMapping("/{id}/active")
    public ResponseEntity<KitchenResponse> updateOrdersActive(@PathVariable long id) throws JsonProcessingException {
        kitchenService.updateStatusOrderActive(id);
        return  ResponseEntity.ok(new KitchenResponse(id,OrderStatus.ORDER_ACTIVE));
    }
    @PostMapping("/{id}/denied")
    public ResponseEntity<KitchenResponse> updateOrdersDenied(@PathVariable long id) throws JsonProcessingException {
        kitchenService.updateStatusOrderDenied(id);
        return  ResponseEntity.ok(new KitchenResponse(id,OrderStatus.ORDER_DENIED));
    }
    @PostMapping("/{id}/complete")
    public ResponseEntity<KitchenResponse> updateOrdersComplete(@PathVariable long id) throws JsonProcessingException {
        kitchenService.updateStatusOrderСompleted(id);
        return  ResponseEntity.ok(new KitchenResponse(id,OrderStatus.ORDER_COMPLETE));
    }

}
