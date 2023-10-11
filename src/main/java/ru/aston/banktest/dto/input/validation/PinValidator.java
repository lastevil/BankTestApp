package ru.aston.banktest.dto.input.validation;


import ru.aston.banktest.exceptions.ValidationException;

public class PinValidator {
    private PinValidator() {
    }

    public static void isValid(String value) {
        if (value.length() == 4 && Patterns.ANY_FOR_NUMBER.matcher(value).matches()) {
            return;
        }
        throw new ValidationException("Pin is not correct");
    }
}
