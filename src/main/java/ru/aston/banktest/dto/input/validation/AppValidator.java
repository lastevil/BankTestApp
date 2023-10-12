package ru.aston.banktest.dto.input.validation;

import ru.aston.banktest.exceptions.ValidationException;

import java.math.BigDecimal;

public class AppValidator {
    private AppValidator() {
    }

    public static void validateBill(String fromBill, String toBill) {
        if (fromBill.equals(toBill)) {
            throw new ValidationException("Bills must be different");
        }
    }

    public static void validateMoney(BigDecimal value) {
        if (value.compareTo(BigDecimal.ZERO) > 0) {
            return;
        }
        throw new ValidationException("Amount must be more than zero");
    }

    public static void validatePin(String value) {
        if (value.length() == 4 && Patterns.ANY_FOR_NUMBER.matcher(value).matches()) {
            return;
        }
        throw new ValidationException("Pin is not correct");
    }
}
