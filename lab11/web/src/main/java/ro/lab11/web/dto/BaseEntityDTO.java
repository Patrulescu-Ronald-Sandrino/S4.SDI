package ro.lab11.web.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Data
@NoArgsConstructor
@SuperBuilder
public class BaseEntityDTO<ID extends Serializable> implements Serializable {
    private ID id;
}
