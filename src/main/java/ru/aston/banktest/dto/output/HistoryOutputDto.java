package ru.aston.banktest.dto.output;

import io.swagger.v3.oas.annotations.media.Schema;
import ru.aston.banktest.core.entity.Actions;
import ru.aston.banktest.core.entity.History;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

public record HistoryOutputDto(@Schema(title = "Действие над операцией", description = "Действие над операцией")
                               Actions action,
                               @Schema(title = "Номер счета списания", description = "Номер счета спсания средств",
                                       example = "123456789101112131")
                               String fromBill,
                               @Schema(title = "Номер счета поступления", description = "Номер счета поступления средств",
                                       example = "123456789101112131")
                               String toBill,
                               @Schema(title = "Сумма", description = "Сумма изменения счета",
                                       example = "300")
                               BigDecimal amount,
                               @Schema(title = "Время операции", description = "Время совершения операции")
                               ZonedDateTime time) {
    public static HistoryOutputDto create(History history) {
        String fromBillNumber;
        String toBillNumber;
        if (history.getAction().equals(Actions.DEPOSITE)) {
            fromBillNumber = "";
            if (history.getToBill() != null) {
                toBillNumber = history.getToBill().getBillNumber();
            } else {
                toBillNumber = "";
            }
        } else if (history.getAction().equals(Actions.WITHDRAW)) {
            toBillNumber = "";
            if (history.getFromBill() != null) {
                fromBillNumber = history.getFromBill().getBillNumber();
            } else {
                fromBillNumber = "";
            }
        } else {
            if (history.getToBill() != null) {
                toBillNumber = history.getToBill().getBillNumber();
            } else {
                toBillNumber = "";
            }
            if (history.getFromBill() != null) {
                fromBillNumber = history.getFromBill().getBillNumber();
            } else {
                fromBillNumber = "";
            }
        }
        return new HistoryOutputDto(
                history.getAction(),
                fromBillNumber,
                toBillNumber,
                history.getAmount(),
                history.getCreatedAt());

    }
}
