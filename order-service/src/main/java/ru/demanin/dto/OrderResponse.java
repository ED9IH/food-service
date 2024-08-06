package ru.demanin.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ru.demanin.statusOrders.OrderStatus;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class OrderResponse {

    private long id;

    private String orderStatus;
}
