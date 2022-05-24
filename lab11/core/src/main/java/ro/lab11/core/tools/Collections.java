package ro.lab11.core.tools;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
}
