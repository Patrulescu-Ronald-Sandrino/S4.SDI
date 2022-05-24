package ro.lab11.web.converter;

import org.springframework.stereotype.Component;
import ro.lab11.core.domain.Offer;
import ro.lab11.core.domain.OfferPK;
import ro.lab11.web.dto.OfferDTO;

@Component
public class OfferConvertor extends BaseEntityConverter<OfferPK, Offer, OfferDTO> {
    @Override
    public Offer convertDTOToModel(OfferDTO dto) {
        return Offer.builder()
                .id(dto.getId())
                .price(dto.getPrice())
                .build();
    }

    @Override
    public OfferDTO convertModelToDTO(Offer offer) {
        return OfferDTO.builder()
                .id(offer.getId())
                .price(offer.getPrice())
                .build();
    }
}
