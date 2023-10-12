package ru.aston.banktest.dto.output;

import io.swagger.v3.oas.annotations.media.Schema;
import ru.aston.banktest.core.entity.Account;

import java.util.List;

public record AccountOutputDto(
        @Schema(title = "Индентификатор пользователя", description = "Уникальный идентификатор пользователя",
                example = "1")
        Long id,
        @Schema(title = "имя пользователя", description = "имя пользователя",
                example = "test")
        String username,
        List<BillOutputDto> bills) {

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
