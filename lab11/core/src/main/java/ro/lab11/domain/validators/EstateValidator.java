package ro.lab11.domain.validators;

import ro.lab11.domain.Estate;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.stream.Stream;

@Component
public class EstateValidator extends Validator<Estate> {
    @Override
    protected Stream<Map.Entry<Boolean, String>> getPairs(Estate entity) {
        return Stream.of(
                createPair(entity.getAddress().length() < 5, "Estate address' length must be >= 5."),
                createPair(entity.getSurface() <= 0, "Estate surface must be > 0.")
                );
    }
}
