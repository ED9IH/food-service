package ru.demanin.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.demanin.dto.OrderDTO;
import ru.demanin.dto.RabbitMessage;
import ru.demanin.entity.Couriers;
import ru.demanin.entity.Order;
import ru.demanin.entity.Restaurant;
import ru.demanin.mapper.OrderKitchenMapper;
import ru.demanin.rabbitProducerServices.RabbitProducerServiceImpl;
import ru.demanin.repositories.CouriersRepository;
import ru.demanin.repositories.OrdersRepository;
import ru.demanin.repositories.RestaurantRepository;
import ru.demanin.response.KitchenResponse;
import ru.demanin.response.RestaurantResponse;
import ru.demanin.status.CouriersStatus;
import ru.demanin.status.OrderStatus;
import ru.demanin.status.RestaurantStatus;


import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class KitchenService {
    @Autowired
    private final OrderKitchenMapper orderKitchenMapper;
    @Autowired
    private final OrdersRepository ordersRepository;
    @Autowired
    private final RabbitProducerServiceImpl rabbitProducerServiceImpl;
    @Autowired
    private final ObjectMapper objectMapper;
    @Autowired
    private final RestaurantRepository restaurantRepository;
    @Autowired
    private final CouriersRepository couriersRepository;


    public List<OrderDTO> getOrders() {
        return orderKitchenMapper.toOrderDTO(ordersRepository.findAll().stream().filter(order -> order.getStatus().equals(OrderStatus.ORDER_PAID)).collect(Collectors.toList()));
    }
    @Transactional
    public KitchenResponse updateStatusOrderActive(Long id) throws JsonProcessingException {
        Order order = ordersRepository.findById(id).orElseThrow(()-> new RuntimeException("Order not found"));
        order.setStatus(OrderStatus.ORDER_ACTIVE);
        ordersRepository.save(order);
        if(order.getStatus().equals(OrderStatus.ORDER_ACTIVE)){
            RabbitMessage rabbitMessage = new RabbitMessage(order.getId(),"orderQueue","Заказ принят");
            rabbitProducerServiceImpl.sendMessage(objectMapper.writeValueAsString(rabbitMessage),
                    "order");
        }
        return new KitchenResponse(id,order.getStatus());
    }
    @Transactional
    public KitchenResponse updateStatusOrderDenied(Long id) throws JsonProcessingException {
        Order order = ordersRepository.findById(id).orElseThrow(()-> new RuntimeException("Order not found"));
        order.setStatus(OrderStatus.ORDER_DENIED);
        ordersRepository.save(order);
        if(order.getStatus().equals(OrderStatus.ORDER_DENIED)){
            RabbitMessage rabbitMessage = new RabbitMessage(order.getId(),"orderQueue","Заказ откланён");
            rabbitProducerServiceImpl.sendMessage(objectMapper.writeValueAsString(rabbitMessage),
                    "order");
        }
        return new KitchenResponse(id,order.getStatus());
    }
    @Transactional
    public KitchenResponse updateStatusOrderСompleted(Long id) throws JsonProcessingException {
        Order order = ordersRepository.findById(id).orElseThrow(()-> new RuntimeException("Order not found"));
        order.setStatus(OrderStatus.WAITING_FOR_COURIER);
        appointCourier(id);
        ordersRepository.save(order);
        if(order.getStatus().equals(OrderStatus.WAITING_FOR_COURIER)){
            RabbitMessage orderRabbitMessage = new RabbitMessage(order.getId(),"orderQueue",
                    "Заказ готов и ожидает курьера");
            rabbitProducerServiceImpl.sendMessage(objectMapper.writeValueAsString(orderRabbitMessage),
                    "order");

            RabbitMessage deliveryrabbitMessage = new RabbitMessage(order.getId(),"deliveryQueue",
                    "Заказ готов и ожидает курьера");
            rabbitProducerServiceImpl.sendMessage(objectMapper.writeValueAsString(deliveryrabbitMessage),
                    "delivery");

            RabbitMessage notificationMessage = new RabbitMessage(id, "notificationQueue",
                    "Заказ готов и ожидает курьера");
            rabbitProducerServiceImpl.sendMessage(objectMapper.writeValueAsString(notificationMessage),
                    "notificationMessage");
        }


        return new KitchenResponse(id,order.getStatus());
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

        RabbitMessage orderMessage = new RabbitMessage(id, "orderQueue",
                "Курьер назначен и скоро будет у вас");
        rabbitProducerServiceImpl.sendMessage(objectMapper.writeValueAsString(orderMessage),
                "order");

        RabbitMessage notificationMessage = new RabbitMessage(id, "notificationQueue",
                "Курьер назначен и скоро будет у вас");
        rabbitProducerServiceImpl.sendMessage(objectMapper.writeValueAsString(notificationMessage),
                "notificationMessage");
    }

    public double getDistance(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }


    public RestaurantResponse openRestaurant(long id) {
        Restaurant restaurant = restaurantRepository.findById(id).orElseThrow(()-> new RuntimeException("Restaurant not found"));
        restaurant.setRestaurantStatus(RestaurantStatus.OPEN);
        restaurantRepository.save(restaurant);
        return new RestaurantResponse(restaurant.getId(),restaurant.getRestaurantStatus());
    }

    public RestaurantResponse closeRestaurant(long id) {
        Restaurant restaurant = restaurantRepository.findById(id).orElseThrow(()-> new RuntimeException("Restaurant not found"));
        restaurant.setRestaurantStatus(RestaurantStatus.CLOSED);
        restaurantRepository.save(restaurant);
        return new RestaurantResponse(restaurant.getId(),restaurant.getRestaurantStatus());
    }


}
