package ro.lab10.domain.validators;

import org.springframework.stereotype.Component;
import ro.lab10.domain.Agency;

import java.util.AbstractMap;
import java.util.stream.Stream;

@Component
public class AgencyValidator extends Validator<Agency> {
    @Override
    protected Stream<AbstractMap.SimpleEntry<Boolean, String>> getPairs(Agency entity) {
        return Stream.of(
                createPair(entity.getName().isEmpty(), "Agency name must not be empty."),
                createPair(entity.getAddress().length() < 5, "Agency address' length must be >= 5.")
        );
    }
}
