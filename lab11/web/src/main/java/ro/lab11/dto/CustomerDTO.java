package ro.lab11.dto;

import lombok.Data;

@Data
public class CustomerDTO extends BaseEntityDTO<Long> {
    private String name;
    private String email;
}
