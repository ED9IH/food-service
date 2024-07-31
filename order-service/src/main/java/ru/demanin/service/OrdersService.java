package ru.demanin.service;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.support.postprocessor.DelegatingDecompressingPostProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.demanin.dto.CreateOrdersDTO;
import ru.demanin.dto.OrderDTO;
import ru.demanin.dto.OrderItemsDTO;
import ru.demanin.dto.RestaurantMenuItemsDTO;
import ru.demanin.entity.Order;
import ru.demanin.entity.OrderItems;
import ru.demanin.entity.Restaurant;
import ru.demanin.entity.RestaurantMenuItems;
import ru.demanin.mapper.CreateOrdersMapper;
import ru.demanin.mapper.OrderMapper;
import ru.demanin.repositories.*;
import org.springframework.transaction.annotation.Transactional;
import ru.demanin.util.ResponseOrderPost;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

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

    //    Customer REST API
//    GET /orders
    public List<OrderDTO> getAllOrder() {
        return orderMapper.toDtoAll(ordersRepository.findAll());
    }

    //    GET /order/${id}
    public OrderDTO getOrderById(long id) {
        return orderMapper.toDto(ordersRepository.getById(id));
    }

    @Transactional
    public ResponseOrderPost createNewOrder(CreateOrdersDTO createOrdersDTO) {
        Order order = createOrdersMapper.toOrder(createOrdersDTO);
        order.setCustomers(customerRepository.findAll().get(0));
        order.setRestaurants(restaurantRepository.getById(createOrdersDTO.getRestaurant_id()));
        order.setStatus("Created_New_Order");
        order.setTimeStamp(new Date());
        ordersRepository.save(order);

        List<OrderItems> orderItems = order.getOrderItems();
        orderItems.forEach(orderItems1 -> orderItems1.setOrder(order));
        orderItems.forEach(orderItems1 -> orderItems1.setRestaurantMenuItems(restaurantMenuItemsRepository.getById(createOrdersDTO.getMenuItems().stream().map(RestaurantMenuItemsDTO::getId).findAny().get())));
        orderItems.forEach(orderItems1 -> orderItems1.setPrice(getPrice(createOrdersDTO.getMenuItems().stream().map(RestaurantMenuItemsDTO::getId).findAny().get())));
        orderItems.forEach(orderItems1 -> orderItems1.setQuantity(order.getOrderItems().get(0).getQuantity()));
        orderItemRepository.saveAll(orderItems);



        return new ResponseOrderPost();
    }

    @Transactional
    public OrderDTO updateStatus(long id) {
        Order order = ordersRepository.getById(id);
        if (order != (null)) {
            order.setStatus(order.getStatus());
        }
        return orderMapper.toDto(ordersRepository.save(order));
    }


    public Long getPrice(long id) {
        List<RestaurantMenuItems> restaurantMenuItems = restaurantMenuItemsRepository.findAll();
        Long getPrice = restaurantMenuItems.stream().filter(restaurantMenuItems1 -> restaurantMenuItems1.getId() == id).map(RestaurantMenuItems::getPrice).findAny().get();
        return getPrice;

    }


}
