package ro.lab11.core.domain;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
@Table(name = "Estates")
@SuperBuilder
public class Estate extends BaseEntity<Long> {
    @NonNull
    private String address;
    @NonNull
    private double surface;
    private Long customerId;

    @ManyToOne
    @MapsId("id")
    @JoinColumn(name = "customerId")
    Customer customer;

    @OneToMany(mappedBy = "estate", cascade = {CascadeType.ALL})
    @ToString.Exclude
    Set<Offer> offers;
}
