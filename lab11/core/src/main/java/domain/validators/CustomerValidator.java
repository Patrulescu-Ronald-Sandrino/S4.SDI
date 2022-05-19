package domain.validators;

import domain.Customer;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.stream.Stream;

@Component
public class CustomerValidator extends Validator<Customer> {
    @Override
    protected Stream<Map.Entry<Boolean, String>> getPairs(Customer entity) {
        return Stream.of(
                createPair(entity.getName().isEmpty(), "Customer name must not be empty."),
                createPair(entity.getEmail().length() < 5, "Customer email's length must be >= 5.")
        );
    }
}
