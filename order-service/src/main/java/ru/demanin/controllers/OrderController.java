package ru.demanin.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.demanin.dto.CreateOrdersDTO;
import ru.demanin.dto.OrderDTO;
import ru.demanin.dto.OrderResponse;
import ru.demanin.service.OrdersService;
import ru.demanin.statusOrders.OrderStatus;
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
    public ResponseEntity<OrderDTO> getByOrderId(@PathVariable long id) {
        return ResponseEntity.ok(ordersService.getOrderById(id));
    }

    @PostMapping("/orders/add")
    public ResponseEntity<ResponseOrderPost> createdNewOrder(@RequestBody CreateOrdersDTO createOrdersDTO) throws JsonProcessingException {
        ordersService.createNewOrder(createOrdersDTO);
        return ResponseEntity.ok(new ResponseOrderPost().get());
    }

    @PostMapping("/orders/{id}/pay")
    public ResponseEntity<OrderResponse> orderPaid(@PathVariable long id) {
        ordersService.paidOrders(id);
        return ResponseEntity.ok(new OrderResponse(id, String.valueOf(OrderStatus.ORDER_PAID)));

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
