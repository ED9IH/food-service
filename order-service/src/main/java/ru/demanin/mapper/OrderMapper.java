package ru.demanin.mapper;
import org.mapstruct.Mapper;
import ru.demanin.dto.OrderDTO;
import ru.demanin.entity.Order;
import java.util.List;
@Mapper(componentModel = "spring")
public interface OrderMapper {

    List<OrderDTO> toDtoOrder(List<Order> orders);



}
