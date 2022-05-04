package ro.lab10.domain.validators;

import ro.lab10.domain.Agency;

import java.util.AbstractMap;
import java.util.Map;
import java.util.stream.Stream;

public abstract class Validator<T> {
    protected abstract Stream<AbstractMap.SimpleEntry<Boolean, String>> getPairs(T entity);

    public final void validate(T entity) throws ValidatorException {
           getPairs(entity)
                   .filter(Map.Entry::getKey)
                   .map(Map.Entry::getValue)
                   .reduce((s1, s2) -> s1 + "\n" + s2)
                   .ifPresent(s -> {throw new ValidatorException(s);});
    }

    static AbstractMap.SimpleEntry<Boolean, String> createPair(Boolean failed, String message) {
        return new AbstractMap.SimpleEntry<>(failed, message);
    }
}
