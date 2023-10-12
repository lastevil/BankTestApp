package ru.aston.banktest.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ApplicationError {
    private Integer statusCode;
    private String message;
}
