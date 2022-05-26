package ro.lab11.web.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;
import ro.lab11.core.domain.Agency;
import ro.lab11.core.domain.Estate;
import ro.lab11.core.domain.OfferPK;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class OfferDTO extends BaseEntityDTO<OfferPK> {
    private double price;
}
