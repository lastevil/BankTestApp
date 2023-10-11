package ru.aston.banktest.core.service;

import org.springframework.data.domain.Page;
import ru.aston.banktest.contract.history.input.GetHistoryUseCase;
import ru.aston.banktest.contract.history.input.SaveOperationUseCase;
import ru.aston.banktest.contract.history.output.HistoryOutputManager;
import ru.aston.banktest.core.entity.Actions;
import ru.aston.banktest.core.entity.Bill;
import ru.aston.banktest.core.entity.History;
import ru.aston.banktest.dto.output.HistoryOutputDto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class HistoryService implements SaveOperationUseCase, GetHistoryUseCase {
    HistoryOutputManager historyOutputManager;

    public HistoryService(HistoryOutputManager historyOutputManager) {
        this.historyOutputManager = historyOutputManager;
    }

    @Override
    public void saveDipositeHistory(Actions action, Bill billNumber, BigDecimal sum) {
        historyOutputManager.save(action, billNumber, billNumber, sum);
    }

    @Override
    public void saveTransferHistory(Actions action, Bill from, Bill to, BigDecimal sum) {
        historyOutputManager.save(action, from, to, sum);
    }

    @Override
    public void saveWithdrawHistory(Actions action, Bill fromBill, BigDecimal sum) {
        historyOutputManager.save(action, fromBill, fromBill, sum);
    }

    @Override
    public Page<HistoryOutputDto> getBillHistory(String bill, Integer page, Integer elementsCount,
                                                 LocalDateTime before, LocalDateTime after) {
        Page<History> historyPage = historyOutputManager.getBillHistory(bill, page, elementsCount, before, after);
        return historyPage.map(HistoryOutputDto::create);

    }
}
