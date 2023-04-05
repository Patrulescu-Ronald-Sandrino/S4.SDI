package ro.lab11.core.domain;


import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
@Table(name = "Offers")
@SuperBuilder
public class Offer extends BaseEntity<OfferPK> {
    @NonNull // 'warning: not null is meaningless on a primitive', but without it doesn't work either
    private double price;

    public Offer(OfferPK offerPK, @NonNull double price) {
        super(offerPK);
        this.price = price;
    }

    @ManyToOne
    @MapsId("id")
    @JoinColumn(name = "agencyId")
    Agency agency;

    @ManyToOne
    @MapsId("id")
    @JoinColumn(name = "estateId")
    Estate estate;
}
