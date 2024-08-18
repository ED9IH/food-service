package ru.demanin.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.demanin.dto.DeliveryDto;
import ru.demanin.dto.RabbitMessage;
import ru.demanin.entity.Couriers;
import ru.demanin.entity.Order;
import ru.demanin.mapper.DeliveryMapper;
import ru.demanin.rabbitProducerServices.RabbitProducerServiceImpl;
import ru.demanin.repositories.CouriersRepository;
import ru.demanin.repositories.OrdersRepository;
import ru.demanin.response.CouriersResponse;
import ru.demanin.status.CouriersStatus;
import ru.demanin.status.OrderStatus;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DeliveryService {
    @Autowired
    private DeliveryMapper deliveryMapper;
    @Autowired
    private OrdersRepository ordersRepository;
    @Autowired
    private CouriersRepository couriersRepository;
    @Autowired
    private RabbitProducerServiceImpl rabbitProducerService;
    @Autowired
    private final ObjectMapper objectMapper;

    public List<DeliveryDto> getAllDeliveryActive() {
        return deliveryMapper.toDto(ordersRepository.findAll()
                .stream().filter(order -> order.getStatus().equals(OrderStatus.WAITING_FOR_COURIER))
                .collect(Collectors.toList()));
    }

    public List<DeliveryDto> getAllDeliveryCompleted() {
        return deliveryMapper.toDto(ordersRepository.findAll()
                .stream().filter(order -> order.getStatus().equals(OrderStatus.ORDER_COMPLETE))
                .collect(Collectors.toList()));
    }


    @Transactional
    public CouriersResponse acceptedOrder(long id) {
        Order order = ordersRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));
        order.setStatus(OrderStatus.COURIER_ACCEPTED_ORDER);
        ordersRepository.save(order);
        return new CouriersResponse(id, "Accepted order");
    }
    @Transactional
    public CouriersResponse completedOrder(long id) {
        Order order = ordersRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));
        order.setStatus(OrderStatus.ORDER_COMPLETE);
        ordersRepository.save(order);
        return new CouriersResponse(id, "Курьер завешил заказ");
    }

}
