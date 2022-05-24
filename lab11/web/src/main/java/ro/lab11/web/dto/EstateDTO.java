package ro.lab11.web.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;
import ro.lab11.core.domain.Customer;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class EstateDTO extends BaseEntityDTO<Long> {
    private String address;
    private double surface;
    private Customer customer;
}
