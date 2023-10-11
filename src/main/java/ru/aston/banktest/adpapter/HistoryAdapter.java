package ru.aston.banktest.adpapter;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import ru.aston.banktest.adpapter.specifications.HistorySpecification;
import ru.aston.banktest.contract.history.output.HistoryOutputManager;
import ru.aston.banktest.core.entity.Actions;
import ru.aston.banktest.core.entity.Bill;
import ru.aston.banktest.core.entity.History;
import ru.aston.banktest.core.repository.HistoryRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class HistoryAdapter implements HistoryOutputManager {
    private final HistoryRepository repository;

    public HistoryAdapter(HistoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public void save(Actions actions, Bill fromBill, Bill toBill, BigDecimal sum) {
        repository.save(new History(actions, fromBill, toBill, sum));
    }

    @Override
    public Page<History> getBillHistory(String bill, Integer page, Integer elementsCount, LocalDateTime before, LocalDateTime after) {
        Specification<History> historySpecification = HistorySpecification.whereBill(bill);
        if (before != null) {
            historySpecification = historySpecification.and(HistorySpecification.dateBeforeThat(before));
        }
        if (after != null) {
            historySpecification = historySpecification.and(HistorySpecification.dateAfterThat(after));
        }
        return repository.findAll(historySpecification, PageRequest.of(page - 1, elementsCount, Sort.by(Sort.Direction.DESC, "createdAt")));
    }
}
