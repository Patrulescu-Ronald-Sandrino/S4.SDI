package ro.lab11.web.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Data
@NoArgsConstructor
@SuperBuilder
public class OfferPKDTO implements Serializable {
    private Long agencyId;
    private Long estateId;
}
