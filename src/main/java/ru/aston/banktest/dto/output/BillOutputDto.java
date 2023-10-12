package ru.aston.banktest.dto.output;

import io.swagger.v3.oas.annotations.media.Schema;
import ru.aston.banktest.core.entity.Bill;

import java.math.BigDecimal;

public record BillOutputDto(
        @Schema(title = "имя пользователя", description = "имя пользователя",
                example = "test") Long id,
        @Schema(title = "Номер счета", description = "Номер счета",
                example = "123456789101112131")
        String billNumber,
        @Schema(title = "Баланс счёта", description = "Баланс счёта",
                example = "500")
        BigDecimal balance) {
    public static BillOutputDto create(Bill bill) {
        return new BillOutputDto(bill.getId(), bill.getBillNumber(), bill.getBalance());
    }
}
