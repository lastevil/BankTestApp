package ru.aston.banktest.dto.input.account;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

public record AccountInputDto(
        String username,
        @NotBlank
        @NotEmpty
        String pinCode) {
}
