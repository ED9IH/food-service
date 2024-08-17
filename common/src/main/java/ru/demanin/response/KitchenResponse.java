package ru.demanin.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.demanin.status.OrderStatus;

@Getter
@Setter
@AllArgsConstructor
public class KitchenResponse {

    private long id;

    private OrderStatus status;

}
