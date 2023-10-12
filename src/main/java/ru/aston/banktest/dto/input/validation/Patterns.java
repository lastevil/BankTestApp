package ru.aston.banktest.dto.input.validation;

import java.util.regex.Pattern;

public class Patterns {
    private Patterns(){}
    public static final Pattern ANY_FOR_NUMBER = Pattern.compile("[[0-9]\\d]{4}");
}
