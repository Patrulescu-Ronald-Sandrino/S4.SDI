package ro.lab11.web.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class CustomerDTO extends BaseEntityDTO<Long> {
    private String name;
    private String email;
}
