package ru.aston.banktest.contract.account.input;

import ru.aston.banktest.dto.output.AccountOutputDto;

public interface AccountGetUseCase {
    AccountOutputDto getAccountInfo(String username);
}
