package ro.lab11.domain.validators;

import ro.lab11.domain.Agency;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.stream.Stream;

@Component
public class AgencyValidator extends Validator<Agency> {
    @Override
    protected Stream<Map.Entry<Boolean, String>> getPairs(Agency entity) {
        return Stream.of(
                createPair(entity.getName().isEmpty(), "Agency name must not be empty."),
                createPair(entity.getAddress().length() < 5, "Agency address' length must be >= 5.")
        );
    }
}
