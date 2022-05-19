package domain.validators;

import domain.exceptions.ValidatorException;
import ro.lab10.domain.Pair;

import java.util.stream.Stream;

public abstract class Validator<T> {
    protected abstract Stream<Pair<Boolean, String>> getPairs(T entity);

    public final void validate(T entity) throws ValidatorException {
           getPairs(entity)
                   .filter(Pair::getLeft)
                   .map(Pair::getRight)
                   .reduce((s1, s2) -> s1 + "\n" + s2)
                   .ifPresent(s -> {throw new ValidatorException(s);});
    }

    static Pair<Boolean, String> createPair(Boolean failed, String message) {
        return new Pair<>(failed, message);
    }
}
