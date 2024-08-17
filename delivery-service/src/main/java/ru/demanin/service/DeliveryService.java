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
    public void appointCourier(long id) throws JsonProcessingException {
        Order order = ordersRepository.getById(id);
        List<Couriers> couriers = couriersRepository.findAll().stream()
                .filter(courier -> courier.getStatus().equals(CouriersStatus.COURIER_AVAILABLE))
                .toList();

        String x = order.getRestaurants().getCoordinates();
        String[] coordinates = x.split(",");
        double latitude = Double.parseDouble(coordinates[0]);
        double longitude = Double.parseDouble(coordinates[1]);
        double closestDistance = Double.MAX_VALUE;
        Couriers closestCourier = null;

        for (Couriers courier : couriers) {
            String[] coords = courier.getCoordinates().split(",");
            double courierLatitude = Double.parseDouble(coords[0]);
            double courierLongitude = Double.parseDouble(coords[1]);
            double distance = getDistance(latitude, longitude, courierLatitude, courierLongitude);

            if (distance < closestDistance) {
                closestDistance = distance;
                closestCourier = courier;
            }

        }
        order.setCouriers(closestCourier);
        ordersRepository.save(order);

        RabbitMessage rabbitMessage = new RabbitMessage(id, "orderQueue", "Курьер назначен и скоро будет у вас");
        rabbitProducerService.sendMessage(objectMapper.writeValueAsString(rabbitMessage), "order");
    }

    public double getDistance(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
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
