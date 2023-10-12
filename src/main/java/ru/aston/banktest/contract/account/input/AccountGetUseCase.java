package ru.aston.banktest.contract.account.input;

import org.springframework.data.domain.Page;
import ru.aston.banktest.dto.output.AccountOutputDto;

public interface AccountGetUseCase {
    AccountOutputDto getAccountInfo(String username);

    Page<AccountOutputDto> getAllAccountInfo(int page, int elementsCount);
}
