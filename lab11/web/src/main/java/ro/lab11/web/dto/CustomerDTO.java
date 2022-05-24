package ro.lab11.web.dto;

import lombok.Data;

@Data
public class CustomerDTO extends BaseEntityDTO<Long> {
    private String name;
    private String email;
}
