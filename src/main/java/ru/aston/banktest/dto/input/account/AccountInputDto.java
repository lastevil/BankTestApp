package ru.aston.banktest.dto.input.account;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

public record AccountInputDto(
        @Schema(title = "Имя пользователя", description = "Имя пользователя",
                example = "test", requiredMode = Schema.RequiredMode.REQUIRED)
        String username,
        @NotBlank
        @NotEmpty
        @Schema(title = "Пин-код", description = "Пин-код для доступа к счету",
                example = "1234", requiredMode = Schema.RequiredMode.REQUIRED)
        String pinCode
) {
}
