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
@Getter
@Setter
@ToString(callSuper = true)
@Table(name = "Customers")
@SuperBuilder
public class Customer extends BaseEntity<Long> {
    private String name;
    private String email;

    @OneToMany(mappedBy = "customer", cascade = {CascadeType.ALL})
    Set<Estate> offers;
}
