package ru.demanin.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.demanin.dto.CreateOrdersDTO;
import ru.demanin.dto.DeliveryDto;
import ru.demanin.dto.OrderDTO;
import ru.demanin.service.OrdersService;
import ru.demanin.util.ResponseOrderPost;

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
    public ResponseEntity<List<OrderDTO>> getOrder() {
        return ResponseEntity.ok(ordersService.getAllOrder());
    }
    @GetMapping("/orders/{id}")
    public ResponseEntity<OrderDTO> getByOrderId (@PathVariable long id){
        return  ResponseEntity.ok(ordersService.getOrderById(id));
    }

    @PostMapping("/orders/add")
    public ResponseEntity<ResponseOrderPost> save(@RequestBody CreateOrdersDTO createOrdersDTO){
      ordersService.save(createOrdersDTO);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseOrderPost().get());
    }

    @GetMapping("/deliveries/{status}")
    public ResponseEntity<List<DeliveryDto>>getAllDeliveries(@PathVariable String status){
        return ResponseEntity.ok(ordersService.getAllDelivery(status));
    }

    @PostMapping("/deliveries/{id}")
    public ResponseEntity<OrderDTO> deliver(@PathVariable long id){
        return ResponseEntity.ok(ordersService.updateStatus(id));
    }







//****************************Test**************************
//    @GetMapping("/orders/test/{id}")
//    public CreateOrdersDTO getByrId (@PathVariable long id){
//        return ordersService.getById(id);
//    }

}
