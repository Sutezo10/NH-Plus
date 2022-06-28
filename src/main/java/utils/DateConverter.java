package utils;

import java.time.LocalDate;
import java.time.LocalTime;

public class DateConverter {

    /**
     * Converts a String to a Local Date
     *
     * @param date is the string which is getting formed
     * @return the LocalDate that got formed
     */
    public static LocalDate convertStringToLocalDate(String date) {
        String[] array = date.split("-");
        return LocalDate.of(Integer.parseInt(array[0]), Integer.parseInt(array[1]),
                Integer.parseInt(array[2]));
    }

    /**
     * Converts a String to a Local Time
     *
     * @param time is the string which is getting formed
     * @return LocalTime that got formed
     */
    public static LocalTime convertStringToLocalTime(String time) {
        String[] array = time.split(":");
        return LocalTime.of(Integer.parseInt(array[0]), Integer.parseInt(array[1]));
    }
}
