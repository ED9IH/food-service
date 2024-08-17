package ru.demanin.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.demanin.dto.DeliveryDto;
import ru.demanin.response.CouriersResponse;
import ru.demanin.service.DeliveryService;

import java.util.List;

@RestController
@RequestMapping("/deliveries")
public class DeliveryController {
    @Autowired
    public  DeliveryService deliveryService;

    @GetMapping("/active")
    public ResponseEntity<List<DeliveryDto>> getAllDeliveriesActive() {
        return ResponseEntity.ok(deliveryService.getAllDeliveryActive());
    }
    @GetMapping("/completed")
    public ResponseEntity<List<DeliveryDto>> getAllDeliveriesCompleted() {
        return ResponseEntity.ok(deliveryService.getAllDeliveryCompleted());
    }
    @PostMapping("/appoint/{id}")
    public ResponseEntity<CouriersResponse> appointCourier(@PathVariable long id) throws JsonProcessingException {
        deliveryService.appointCourier(id);
        return ResponseEntity.ok(new CouriersResponse(id,"Курьер " + id + " назначен"));
    }
    @PostMapping("/completed/{id}")
    public ResponseEntity<CouriersResponse> completedOrder(@PathVariable long id){
        deliveryService.completedOrder(id);
        return ResponseEntity.ok(new CouriersResponse(id,"Закза " + id + " завершен"));
    }
    @PostMapping("/accept/{id}")
    public ResponseEntity<CouriersResponse> acceptedOrder(@PathVariable long id){
        deliveryService.acceptedOrder(id);
        return ResponseEntity.ok(new CouriersResponse(id,"Курьер принял заказ " + id));
    }
}
