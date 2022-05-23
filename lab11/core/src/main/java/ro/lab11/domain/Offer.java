package ro.lab11.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(callSuper = true)
@Table(name = "Offers")
public class Offer extends BaseEntity<OfferPK> {
    private double price;

    @ManyToOne
    @MapsId("id")
    @JoinColumn(name = "agencyId")
    Agency agency;

    @ManyToOne
    @MapsId("id")
    @JoinColumn(name = "estateId")
    Estate estate;
}
