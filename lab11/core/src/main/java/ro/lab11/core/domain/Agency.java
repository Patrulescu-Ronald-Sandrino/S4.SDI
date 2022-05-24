package ro.lab11.core.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(callSuper = true)
@Table(name = "Agencies")
@SuperBuilder
public class Agency extends BaseEntity<Long>{
    private String name;
    private String address;

    @OneToMany(mappedBy = "agency", cascade = {CascadeType.ALL})
    Set<Offer> offers;
}
