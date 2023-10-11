package ru.aston.banktest.contract.history.input;

import org.springframework.data.domain.Page;
import ru.aston.banktest.dto.output.HistoryOutputDto;

import java.time.LocalDateTime;

public interface GetHistoryUseCase {
    Page<HistoryOutputDto> getBillHistory(String bill, Integer page, Integer elementsCount, LocalDateTime before, LocalDateTime after);
}
