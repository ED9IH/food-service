package ru.demanin.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.demanin.statusOrders.RestaurantStatus;

@Getter
@Setter
@AllArgsConstructor
public class RestaurantResponse {

    private long restaurantId;

    private RestaurantStatus response;
}
