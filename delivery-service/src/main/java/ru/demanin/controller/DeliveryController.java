package ru.demanin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.demanin.dto.DeliveryDto;
import ru.demanin.service.DeliveryService;

import java.util.List;

@RestController
@RequestMapping
public class DeliveryController {

    public final DeliveryService deliveryService;

    @Autowired
    public DeliveryController(DeliveryService deliveryService) {
        this.deliveryService = deliveryService;
    }

    @GetMapping("/deliveries/{status}")
    public ResponseEntity<List<DeliveryDto>> getAllDeliveries(@PathVariable String status) {
        return ResponseEntity.ok(deliveryService.getAllDelivery(status));
    }
}
