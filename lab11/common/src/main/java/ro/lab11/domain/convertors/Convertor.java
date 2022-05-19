package ro.lab11.domain.convertors;

import java.util.Arrays;
import java.util.List;

public interface Convertor<T> {

    static List<String> getTokens(String message, String separator) {
        return Arrays.asList(message.split(separator));
    }
    String toMessage(T t);
    T fromMessage(String message);
}
