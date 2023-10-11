package ru.aston.banktest.dto.input.bill;

import java.math.BigDecimal;

public record BillWithdrawOperationInputDto(String billNumber, BigDecimal sum, String pinCode) {
}
