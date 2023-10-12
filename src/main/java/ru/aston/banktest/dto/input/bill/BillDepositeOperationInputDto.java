package ru.aston.banktest.dto.input.bill;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

public record BillDepositeOperationInputDto(
        @Schema(title = "Номер счета", description = "Номер счета",
                example = "123456789101112131", requiredMode = Schema.RequiredMode.REQUIRED)
        String billNumber,
        @Schema(title = "Количество денег", description = "Количество денег",
                example = "500", requiredMode = Schema.RequiredMode.REQUIRED)
        BigDecimal sum){
}
