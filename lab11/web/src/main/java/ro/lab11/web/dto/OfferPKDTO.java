package ro.lab11.web.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class OfferPKDTO implements Serializable {
    private Long agencyId;
    private Long estateId;
}
