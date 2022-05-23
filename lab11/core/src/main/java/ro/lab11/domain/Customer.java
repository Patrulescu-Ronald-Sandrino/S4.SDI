package ro.lab11.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

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
@Table(name = "Customers")
public class Customer extends BaseEntity<Long> {
    private String name;
    private String email;

    @OneToMany(mappedBy = "Estates", cascade = {CascadeType.ALL})
    Set<Estate> offers;
}
