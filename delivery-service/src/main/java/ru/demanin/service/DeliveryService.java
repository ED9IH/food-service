package ru.demanin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.demanin.dto.DeliveryDto;
import ru.demanin.mapper.DeliveryMapper;
import ru.demanin.repositories.OrdersRepository;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class DeliveryService {
    @Autowired
    private DeliveryMapper deliveryMapper;
    @Autowired
    private OrdersRepository ordersRepository;




    public List<DeliveryDto> getAllDelivery(String status) {
        return deliveryMapper.toDto(ordersRepository.findAll()
                .stream().filter(order -> order.getStatus().equals(status))
                .collect(Collectors.toList()));
    }
}
