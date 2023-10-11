package ru.aston.banktest.dto.output;

import ru.aston.banktest.core.entity.Actions;
import ru.aston.banktest.core.entity.History;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

public record HistoryOutputDto(String action, String fromBill, String toBill, BigDecimal amount, ZonedDateTime time) {
    public static HistoryOutputDto create(History history) {
        String actionText;
        if (history.getAction().equals(Actions.DEPOSITE)) {
            actionText = "Пополнение";
        } else if (history.getAction().equals(Actions.WITHDRAW)) {
            actionText = "Снятие";
        } else {
            actionText = "Перевод";
        }
        return new HistoryOutputDto(
                actionText,
                history.getFromBill().getBillNumber(),
                history.getToBill().getBillNumber(),
                history.getAmount(),
                history.getCreatedAt()
        );

    }
}
