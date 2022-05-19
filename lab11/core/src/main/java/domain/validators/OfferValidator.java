package domain.validators;

import org.springframework.stereotype.Component;
import ro.lab10.domain.Offer;
import ro.lab10.domain.Pair;

import java.util.stream.Stream;

@Component
public class OfferValidator extends Validator<Offer> {
    @Override
    protected Stream<Pair<Boolean, String>> getPairs(Offer entity) {
        return Stream.of(
                createPair(entity.getPrice() <= 0, "Offer price must be > 0.")
        );
    }
}
