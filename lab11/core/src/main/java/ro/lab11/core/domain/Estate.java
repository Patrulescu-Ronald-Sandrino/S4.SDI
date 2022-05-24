package ro.lab11.core.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(callSuper = true)
@Table(name = "Estates")
public class Estate extends BaseEntity<Long> {
    private String address;
    private double surface;
    private Long customerId;

    @ManyToOne
    @MapsId("id")
    @JoinColumn(name = "customerId")
    Customer customer;

    @OneToMany(mappedBy = "Offers", cascade = {CascadeType.ALL})
    Set<Offer> offers;
}
