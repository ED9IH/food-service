package ru.demanin.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.web.bind.annotation.*;
import ru.demanin.dto.CreateOrdersDTO;
import ru.demanin.dto.OrderDTO;
import ru.demanin.response.OrderResponse;
import ru.demanin.service.OrdersService;
import ru.demanin.status.OrderStatus;


import java.util.List;

@RestController
@RequestMapping
public class OrderController {
    @Autowired
    public  OrdersService ordersService;

    @GetMapping("/orders")
    public ResponseEntity<List<OrderDTO>> getOrder() {
        return ResponseEntity.ok(ordersService.getAllOrder());
    }

    @GetMapping("/orders/{id}")
    public ResponseEntity<OrderDTO> getByOrderId(@PathVariable long id) {
        return ResponseEntity.ok(ordersService.getOrderById(id));
    }

    @PostMapping("/orders/add")
    public ResponseEntity<OrderResponse> createdNewOrder(@RequestBody CreateOrdersDTO createOrdersDTO) throws JsonProcessingException {
        return ResponseEntity.ok(ordersService.createNewOrder(createOrdersDTO));
    }

    @PostMapping("/orders/{id}/pay")
    public ResponseEntity<OrderResponse> orderPaid(@PathVariable long id) throws JsonProcessingException {
        ordersService.paidOrders(id);
        return ResponseEntity.ok(new OrderResponse(id, OrderStatus.ORDER_PAID,"Заказ оплачен"));

    }
    @GetMapping("/orders/12345")
    public ResponseEntity<OrderResponse> asd(){
        ordersService.appointCourier(1);
        return ResponseEntity.ok(new OrderResponse(15,OrderStatus.ORDER_PAID,"asd"));
    }




//    @PostMapping("/deliveries/{id}")
//    public ResponseEntity<OrderDTO> deliver(@PathVariable long id) {
//        return ResponseEntity.ok(ordersService.updateStatus(id));
//    }


//****************************Test**************************
//    @GetMapping("/orders/test/{id}")
//    public CreateOrdersDTO getByrId (@PathVariable long id){
//        return ordersService.getById(id);
//    }

}
