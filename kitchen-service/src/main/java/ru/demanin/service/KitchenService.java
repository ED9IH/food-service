package ru.demanin.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.demanin.dto.OrderDTO;
import ru.demanin.mapper.OrderItemMapper;
import ru.demanin.mapper.OrderKitchenMapper;
import ru.demanin.repositories.OrdersRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class KitchenService {
    @Autowired
    private final OrderKitchenMapper orderKitchenMapper;
    @Autowired
    private final OrderItemMapper orderItemMapper;
    @Autowired
    private final OrdersRepository ordersRepository;



    public List<OrderDTO> getAllDelivery() {
        return orderKitchenMapper.toOrderDTO(ordersRepository.findAll());

    }
    //    public List<OrderDTO> getAllDelivery(String status) {
//        return orderMapper.toOrderDTO(ordersRepository.findAll()
//                .stream().filter(order -> order.getStatus().equals(status))
//                .collect(Collectors.toList()));
//    }
}
