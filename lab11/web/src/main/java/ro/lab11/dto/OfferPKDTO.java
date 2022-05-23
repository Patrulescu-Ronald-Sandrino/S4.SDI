package ro.lab11.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class OfferPKDTO implements Serializable {
    private Long agencyId;
    private Long estateId;
}
