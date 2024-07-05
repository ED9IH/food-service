package ru.demanin.mapper;

import org.mapstruct.Mapper;
import ru.demanin.dto.CustomerForDeliveryDTO;
import ru.demanin.entity.Customer;

@Mapper(componentModel = "spring")
public interface CustomerForDeliveryMapper {

    CustomerForDeliveryDTO toDto(Customer customer);

    Customer toEntity(CustomerForDeliveryDTO customerForDeliveryDTO);
}
