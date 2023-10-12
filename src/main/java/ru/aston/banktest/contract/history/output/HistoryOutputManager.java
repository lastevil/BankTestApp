package ru.aston.banktest.contract.history.output;

import org.springframework.data.domain.Page;
import ru.aston.banktest.core.entity.Actions;
import ru.aston.banktest.core.entity.Bill;
import ru.aston.banktest.core.entity.History;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface HistoryOutputManager {
    void save(Actions actions, Bill fromBill, Bill toBill, BigDecimal sum);

    Page<History> getBillHistory(String bill, Integer page, Integer elementsCount, LocalDateTime before, LocalDateTime after);
}
