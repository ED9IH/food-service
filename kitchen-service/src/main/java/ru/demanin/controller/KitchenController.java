package ru.demanin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.demanin.dto.OrderDTO;
import ru.demanin.service.KitchenService;

import java.util.List;

@RestController
@RequestMapping
public class KitchenController {

    private final KitchenService kitchenService;
    @Autowired
    public KitchenController(KitchenService kitchenService) {
        this.kitchenService = kitchenService;
    }
    @GetMapping("/123")
    public ResponseEntity<List<OrderDTO>> getOrders() {
        return  ResponseEntity.ok(kitchenService.getOrders());
    }
}
