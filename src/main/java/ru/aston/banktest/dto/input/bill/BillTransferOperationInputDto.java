package ru.aston.banktest.dto.input.bill;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

public record BillTransferOperationInputDto(
        @Schema(title = "Номер счета для списания", description = "Номер счета для списания",
                example = "123456789101112131", requiredMode = Schema.RequiredMode.REQUIRED)
        String fromBill,
        @Schema(title = "Номер счета для зачисления", description = "Номер счета для зачисления",
                example = "123456789101112131", requiredMode = Schema.RequiredMode.REQUIRED)
        String toBill,
        @Schema(title = "Количество денег", description = "Количество денег",
                example = "500", requiredMode = Schema.RequiredMode.REQUIRED)
        BigDecimal sum,
        @Schema(title = "Пин-код", description = "Пин-код для доступа к счету",
                example = "1234", requiredMode = Schema.RequiredMode.REQUIRED)
        String pinCode) {
}
