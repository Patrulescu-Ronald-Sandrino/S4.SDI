package ro.lab11.domain.validators;

import ro.lab11.domain.exceptions.ValidatorException;

import java.util.AbstractMap;
import java.util.Map;
import java.util.stream.Stream;

public abstract class Validator<T> {
    protected abstract Stream<Map.Entry<Boolean, String>> getPairs(T entity);

    public final void validate(T entity) throws ValidatorException {
           getPairs(entity)
                   .filter(Map.Entry::getKey)
                   .map(Map.Entry::getValue)
                   .reduce((s1, s2) -> s1 + "\n" + s2)
                   .ifPresent(s -> {throw new ValidatorException(s);});
    }

    static Map.Entry<Boolean, String> createPair(Boolean failed, String message) {
        return new AbstractMap.SimpleEntry<>(failed, message);
    }
}
