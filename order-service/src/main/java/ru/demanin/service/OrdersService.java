package ru.demanin.service;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.demanin.dto.*;
import ru.demanin.entity.Order;
import ru.demanin.entity.OrderItems;
import ru.demanin.entity.RestaurantMenuItems;
import ru.demanin.mapper.CreateOrdersMapper;
import ru.demanin.mapper.OrderMapper;
import ru.demanin.rabbitProducerService.RabbitProducerServiceImpl;
import ru.demanin.repositories.*;
import org.springframework.transaction.annotation.Transactional;
import ru.demanin.statusOrders.OrderStatus;
import ru.demanin.util.ResponseOrderPost;
import java.util.*;

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
    public ResponseOrderPost createNewOrder(CreateOrdersDTO createOrdersDTO) throws JsonProcessingException {
        Order order = createOrdersMapper.toOrder(createOrdersDTO);
        order.setCustomers(customerRepository.findAll().get(0));
        order.setRestaurants(restaurantRepository.getById(createOrdersDTO.getRestaurant_id()));
        order.setStatus(String.valueOf(OrderStatus.ORDER_CREATED));
        order.setTimeStamp(new Date());
        ordersRepository.save(order);

        List<OrderItems> orderItems = order.getOrderItems();
        orderItems.forEach(orderItems1 -> orderItems1.setOrder(order));
        orderItems.forEach(orderItems1 -> orderItems1.setRestaurantMenuItems(restaurantMenuItemsRepository.getById(createOrdersDTO.getMenuItems().stream().map(RestaurantMenuItemsDTO::getId).findAny().get())));
        orderItems.forEach(orderItems1 -> orderItems1.setPrice(getPrice(createOrdersDTO.getMenuItems().stream().map(RestaurantMenuItemsDTO::getId).findAny().get())));
        orderItems.forEach(orderItems1 -> orderItems1.setQuantity(order.getOrderItems().get(0).getQuantity()));
        orderItemRepository.saveAll(orderItems);
        RabbitMessage rabbitMessage = new RabbitMessage
                (order.getId(), "kitchen", "Новый заказ  ожидает подтверждения.");

        rabbitProducerServiceImpl.sendMessage(objectMapper.writeValueAsString(rabbitMessage),
                "notification");

        return new ResponseOrderPost();
    }

    @Transactional
    public Order paidOrders(long id) {
        Order order = ordersRepository.getById(id);
        order.setStatus(String.valueOf(OrderStatus.ORDER_PAID));
        System.out.println(id);
        return ordersRepository.save(order);
    }


    public Long getPrice(long id) {
        List<RestaurantMenuItems> restaurantMenuItems = restaurantMenuItemsRepository.findAll();
        Long getPrice = restaurantMenuItems.stream().filter(restaurantMenuItems1 -> restaurantMenuItems1.getId() == id).map(RestaurantMenuItems::getPrice).findAny().get();
        return getPrice;

    }

}
