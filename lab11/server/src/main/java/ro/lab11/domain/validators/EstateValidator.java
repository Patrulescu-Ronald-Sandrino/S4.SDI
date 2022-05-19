package ro.lab11.domain.validators;

import org.springframework.stereotype.Component;
import ro.lab11.domain.Estate;
import ro.lab11.domain.Pair;

import java.util.stream.Stream;

@Component
public class EstateValidator extends Validator<Estate> {
    @Override
    protected Stream<Pair<Boolean, String>> getPairs(Estate entity) {
        return Stream.of(
                createPair(entity.getAddress().length() < 5, "Estate address' length must be >= 5."),
                createPair(entity.getSurface() <= 0, "Estate surface must be > 0.")
                );
    }
}
