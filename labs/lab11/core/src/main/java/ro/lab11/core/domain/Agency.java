package ro.lab11.core.domain;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
@Table(name = "Agencies")
@SuperBuilder
public class Agency extends BaseEntity<Long>{
    @NonNull
    private String name;
    @NonNull
    private String address;

    @OneToMany(mappedBy = "agency", cascade = {CascadeType.ALL})
    @ToString.Exclude
    Set<Offer> offers;
}
