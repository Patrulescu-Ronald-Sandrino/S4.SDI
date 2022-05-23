package ro.lab11.dto;

import lombok.Data;

@Data
public class AgencyDTO extends BaseEntityDTO<Long> {
    private String name;
    private String address;
}
