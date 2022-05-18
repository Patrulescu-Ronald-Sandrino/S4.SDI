package ro.lab10;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Tools {
    public static String getDateTime() {
        var dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        var currentTime = LocalDateTime.now();
        return dateTimeFormatter.format(currentTime);
    }
}
