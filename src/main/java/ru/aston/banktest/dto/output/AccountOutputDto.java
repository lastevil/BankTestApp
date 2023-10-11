package ru.aston.banktest.dto.output;

import ru.aston.banktest.core.entity.Account;

import java.util.List;

public record AccountOutputDto(Long id, String username, List<BillOutputDto> bills) {

    public static AccountOutputDto fromEntityToDto(Account account) {
        return new AccountOutputDto(
                account.getId(),
                account.getUsername(),
                account.getBillList().stream().map(
                        BillOutputDto::create
                ).toList()
        );
    }
}
