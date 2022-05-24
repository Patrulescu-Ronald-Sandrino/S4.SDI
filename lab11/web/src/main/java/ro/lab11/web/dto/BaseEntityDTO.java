package ro.lab11.web.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class BaseEntityDTO<ID extends Serializable> implements Serializable {
    private ID id;
}
