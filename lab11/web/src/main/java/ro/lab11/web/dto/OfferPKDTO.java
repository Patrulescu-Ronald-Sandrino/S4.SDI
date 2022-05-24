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
public class OfferPKDTO implements Serializable {
    private Long agencyId;
    private Long estateId;
}
