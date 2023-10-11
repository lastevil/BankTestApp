package ru.aston.banktest.dto.input.bill;

import java.math.BigDecimal;

public record BillTransferOperationInputDto(String fromBill, String toBill, BigDecimal sum, String pinCode) {
}
