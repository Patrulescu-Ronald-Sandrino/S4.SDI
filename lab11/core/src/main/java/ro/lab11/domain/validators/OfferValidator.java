package ro.lab11.domain.validators;

import ro.lab11.domain.Offer;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.stream.Stream;

@Component
public class OfferValidator extends Validator<Offer> {
    @Override
    protected Stream<Map.Entry<Boolean, String>> getPairs(Offer entity) {
        return Stream.of(
                createPair(entity.getPrice() <= 0, "Offer price must be > 0.")
        );
    }
}
