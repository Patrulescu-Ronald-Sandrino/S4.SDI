package ro.lab10.domain.validators;

import org.springframework.stereotype.Component;
import ro.lab10.domain.Offer;

import java.util.AbstractMap;
import java.util.stream.Stream;

@Component
public class OfferValidator extends Validator<Offer> {
    @Override
    protected Stream<AbstractMap.SimpleEntry<Boolean, String>> getPairs(Offer entity) {
        return Stream.of(
                createPair(entity.getPrice() <= 0, "Offer price must be > 0.")
        );
    }
}
