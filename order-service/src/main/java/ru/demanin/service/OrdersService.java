package ru.demanin.service;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.demanin.dto.OrderDTO;
import ru.demanin.entity.Order;
import ru.demanin.mapper.CreateOrdersMapper;
import ru.demanin.mapper.OrderMapper;
import ru.demanin.repositories.OrdersRepository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
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
    public OrderDTO updateStatus(long id) {
        Order order = ordersRepository.getById(id);
        if (order != (null)) {
            order.setStatus(order.getStatus());
        }
        return orderMapper.toDto(ordersRepository.save(order));
    }

}
