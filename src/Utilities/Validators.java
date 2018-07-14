package Utilities;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Validators {
    public static boolean isValidDate(String input) {
        boolean isValid = true;
        try {
            LocalDate.parse(input);
        } catch (DateTimeParseException e) {
            isValid = false;
        }
        return isValid;
    }
}
