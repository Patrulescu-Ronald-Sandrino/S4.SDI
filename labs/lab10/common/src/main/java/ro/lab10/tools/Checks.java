package ro.lab10.tools;

public class Checks {
    public static <T> void ThrowIfNull(T reference, String message) throws IllegalArgumentException {
        if (reference == null) {
            throw new IllegalArgumentException(message);
        }
    }
}
