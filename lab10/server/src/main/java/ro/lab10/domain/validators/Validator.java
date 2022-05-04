package ro.lab10.domain.validators;

public interface Validator<T> {
    void validate(T entity) throws ValidatorException;
}
