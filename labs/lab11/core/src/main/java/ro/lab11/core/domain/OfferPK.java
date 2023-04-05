package ro.lab11.core.domain;

import lombok.*;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class OfferPK implements Serializable {
    private Long agencyId;
    private Long estateId;
}
