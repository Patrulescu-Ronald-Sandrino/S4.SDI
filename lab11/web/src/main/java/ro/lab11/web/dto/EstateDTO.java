package ro.lab11.web.dto;

import lombok.Data;
import ro.lab11.core.domain.Customer;

@Data
public class EstateDTO extends BaseEntityDTO<Long> {
    private String address;
    private double surface;
    private Customer customer;
}
