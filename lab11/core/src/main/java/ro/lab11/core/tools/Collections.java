package ro.lab11.core.tools;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class Collections {
    public static String join(String delimiter, Object... objects) {
        return Arrays.stream(objects)
                .map(Object::toString)
                .collect(Collectors.joining(delimiter));
    }
    public static String join(Stream<Object> objects, String delimiter) {
        return objects
                .map(Object::toString)
                .collect(Collectors.joining(delimiter));
    }

    public static <T> String convertIterableToString(Iterable<T> iterable, String delimiter, String other) {
        return StreamSupport.stream(iterable.spliterator(), false)
                .map(Objects::toString)
                .reduce((s, s2) -> s + delimiter + s2)
                .orElse(other);
    }
}
