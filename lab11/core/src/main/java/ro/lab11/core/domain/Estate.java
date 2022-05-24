package ro.lab11.core.domain;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
@Table(name = "Estates")
@SuperBuilder
public class Estate extends BaseEntity<Long> {
    private String address;
    private double surface;
    private Long customerId;

    @ManyToOne
    @MapsId("id")
    @JoinColumn(name = "customerId")
    Customer customer;

    @OneToMany(mappedBy = "estate", cascade = {CascadeType.ALL})
    Set<Offer> offers;
}
