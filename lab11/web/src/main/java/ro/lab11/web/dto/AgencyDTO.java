package ro.lab11.web.dto;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class AgencyDTO extends BaseEntityDTO<Long> {
    private String name;
    private String address;
}
