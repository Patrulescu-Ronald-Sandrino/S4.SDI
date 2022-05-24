package ro.lab11.web.converter;

import ro.lab11.core.domain.Customer;
import ro.lab11.web.dto.CustomerDTO;

public class CustomerConvertor extends BaseEntityConverter<Long, Customer, CustomerDTO> {
    @Override
    public Customer convertDTOToModel(CustomerDTO dto) {
        return Customer.builder()
                .id(dto.getId())
                .name(dto.getName())
                .email(dto.getEmail())
                .build();
    }

    @Override
    public CustomerDTO convertModelToDTO(Customer customer) {
        return CustomerDTO.builder()
                .id(customer.getId())
                .name(customer.getName())
                .email(customer.getEmail())
                .build();
    }
}
