package ru.demanin.response;

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

    private OrderStatus orderStatus;

    private String secret_payment_url;
}
