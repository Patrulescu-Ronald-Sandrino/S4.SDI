package ro.lab11.web.dto;

import lombok.Data;
import ro.lab11.core.domain.Agency;
import ro.lab11.core.domain.Estate;

@Data
public class OfferDTO extends BaseEntityDTO<OfferPKDTO> {
    private double price;
    private Agency agency;
    private Estate estate;
}
