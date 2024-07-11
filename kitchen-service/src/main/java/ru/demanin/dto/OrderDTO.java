package ru.demanin.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderDTO {

    private int id;

    private List<OrderItemDTO> menu_items;

}
