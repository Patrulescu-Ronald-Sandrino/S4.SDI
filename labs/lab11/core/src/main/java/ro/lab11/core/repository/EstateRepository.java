package ro.lab11.core.repository;

import org.springframework.data.jpa.repository.Query;
import ro.lab11.core.domain.Estate;

import java.util.Set;

public interface EstateRepository extends BaseEntityRepository<Estate, Long> {
    Set<Estate> findByAddressContainingOrderBySurface(String addressSubstring);
}
