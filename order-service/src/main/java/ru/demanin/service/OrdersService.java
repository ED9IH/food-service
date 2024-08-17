package ru.demanin.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.demanin.dto.*;
import ru.demanin.entity.Couriers;
import ru.demanin.entity.Order;
import ru.demanin.entity.OrderItems;
import ru.demanin.entity.RestaurantMenuItems;
import ru.demanin.mapper.CreateOrdersMapper;
import ru.demanin.mapper.OrderMapper;
import ru.demanin.rabbitProducerService.RabbitProducerServiceImpl;
import ru.demanin.repositories.*;
import org.springframework.transaction.annotation.Transactional;
import ru.demanin.response.OrderResponse;
import ru.demanin.status.CouriersStatus;
import ru.demanin.status.OrderStatus;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrdersService {
    @Autowired
    private final OrdersRepository ordersRepository;
    @Autowired
    private final OrderMapper orderMapper;
    @Autowired
    private final CreateOrdersMapper createOrdersMapper;
    @Autowired
    private final OrderItemRepository orderItemRepository;
    @Autowired
    private final CustomerRepository customerRepository;
    @Autowired
    private final RestaurantRepository restaurantRepository;
    @Autowired
    private final RestaurantMenuItemsRepository restaurantMenuItemsRepository;
    @Autowired
    private final CouriersRepository couriersRepository;
    @Autowired
    private final RabbitProducerServiceImpl rabbitProducerServiceImpl;
    @Autowired
    private final ObjectMapper objectMapper;

    public List<OrderDTO> getAllOrder() {
        return orderMapper.toDtoAll(ordersRepository.findAll());
    }

    public OrderDTO getOrderById(long id) {
        return orderMapper.toDto(ordersRepository.getById(id));
    }

    @Transactional
    public OrderResponse createNewOrder(CreateOrdersDTO createOrdersDTO) throws JsonProcessingException {
        Order order = createOrdersMapper.toOrder(createOrdersDTO);
        order.setCustomers(customerRepository.findAll().get(0));
        order.setRestaurants(restaurantRepository.getById(createOrdersDTO.getRestaurant_id()));
        order.setStatus(OrderStatus.ORDER_CREATED);
        order.setTimeStamp(new Date());
        ordersRepository.save(order);

        List<OrderItems> orderItems = order.getOrderItems();
        orderItems.forEach(orderItems1 -> orderItems1.setOrder(order));
        orderItems.forEach(orderItems1 -> orderItems1.setRestaurantMenuItems(restaurantMenuItemsRepository.getById(createOrdersDTO.getMenuItems().stream().map(RestaurantMenuItemsDTO::getId).findAny().get())));
        orderItems.forEach(orderItems1 -> orderItems1.setPrice(getPrice(createOrdersDTO.getMenuItems().stream().map(RestaurantMenuItemsDTO::getId).findAny().get())));
        orderItemRepository.saveAll(orderItems);
        RabbitMessage orderQueue = new RabbitMessage
                (order.getId(), "orderQueue", "Новый заказ создан и ожидает оплаты.");
        rabbitProducerServiceImpl.sendMessage(objectMapper.writeValueAsString(orderQueue),
                "order");
        RabbitMessage notificationQueue = new RabbitMessage
                (order.getId(), "notificationQueue", "Новый заказ создан и ожидает оплаты.");
        rabbitProducerServiceImpl.sendMessage(objectMapper.writeValueAsString(notificationQueue),
                "notification");
        return new OrderResponse(order.getId(), OrderStatus.ORDER_CREATED, "secret_payment_url");
    }

    @Transactional
    public Order paidOrders(long id) throws JsonProcessingException {
        Order order = ordersRepository.getById(id);
        order.setStatus(OrderStatus.ORDER_PAID);
        RabbitMessage rabbitMessage = new RabbitMessage
                (order.getId(), "kitchenQueue", "Новый заказ оплачен и ожидает подтверждения.");
        rabbitProducerServiceImpl.sendMessage(objectMapper.writeValueAsString(rabbitMessage),
                "kitchen");
        return ordersRepository.save(order);
    }


    public Long getPrice(long id) {
        List<RestaurantMenuItems> restaurantMenuItems = restaurantMenuItemsRepository.findAll();
        Long getPrice = restaurantMenuItems.stream().filter(restaurantMenuItems1 -> restaurantMenuItems1.getId() == id).map(RestaurantMenuItems::getPrice).findAny().get();
        return getPrice;

    }

    public void appointCourier(long id) {
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
    }

    public double getDistance(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }


}
