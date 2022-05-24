package ro.lab11.core.domain;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.Hibernate;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Objects;

@MappedSuperclass
@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
// java all args constructor call super constructor - Alan Ho - https://stackoverflow.com/questions/29740078/how-to-call-super-constructor-in-lombok
// https://stackoverflow.com/questions/59626796/lomboks-superbuilder-error-java-cannot-find-symbol
@SuperBuilder
public class BaseEntity<ID extends Serializable> implements Serializable {
    @Id
    @GeneratedValue
    protected ID id;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        BaseEntity<?> that = (BaseEntity<?>) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
