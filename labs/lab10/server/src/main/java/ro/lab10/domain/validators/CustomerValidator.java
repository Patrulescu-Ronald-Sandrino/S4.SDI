package ro.lab10.domain.validators;

import org.springframework.stereotype.Component;
import ro.lab10.domain.Customer;
import ro.lab10.domain.Pair;

import java.util.stream.Stream;

@Component
public class CustomerValidator extends Validator<Customer> {
    @Override
    protected Stream<Pair<Boolean, String>> getPairs(Customer entity) {
        return Stream.of(
                createPair(entity.getName().isEmpty(), "Customer name must not be empty."),
                createPair(entity.getEmail().length() < 5, "Customer email's length must be >= 5.")
        );
    }
}
