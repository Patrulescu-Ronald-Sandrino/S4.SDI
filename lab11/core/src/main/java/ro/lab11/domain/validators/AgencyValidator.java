package ro.lab11.domain.validators;

import org.springframework.stereotype.Component;
import ro.lab11.domain.Agency;
import ro.lab11.domain.Pair;

import java.util.stream.Stream;

@Component
public class AgencyValidator extends Validator<Agency> {
    @Override
    protected Stream<Pair<Boolean, String>> getPairs(Agency entity) {
        return Stream.of(
                createPair(entity.getName().isEmpty(), "Agency name must not be empty."),
                createPair(entity.getAddress().length() < 5, "Agency address' length must be >= 5.")
        );
    }
}
