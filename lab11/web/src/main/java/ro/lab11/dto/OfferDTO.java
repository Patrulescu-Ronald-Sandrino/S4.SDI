package ro.lab11.dto;

import lombok.Data;
import ro.lab11.domain.Agency;
import ro.lab11.domain.Estate;

@Data
public class OfferDTO extends BaseEntityDTO<OfferPKDTO> {
    private double price;
    private Agency agency;
    private Estate estate;
}
