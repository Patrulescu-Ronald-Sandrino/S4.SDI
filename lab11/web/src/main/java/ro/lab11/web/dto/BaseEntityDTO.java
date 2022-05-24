package ro.lab11.web.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class BaseEntityDTO<ID extends Serializable> implements Serializable {
    private ID id;
}
