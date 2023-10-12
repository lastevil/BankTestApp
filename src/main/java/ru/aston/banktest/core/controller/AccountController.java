package ru.aston.banktest.core.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import ru.aston.banktest.contract.account.input.AccountCreateUseCase;
import ru.aston.banktest.contract.account.input.AccountGetUseCase;
import ru.aston.banktest.dto.input.account.AccountInputDto;
import ru.aston.banktest.dto.input.validation.AppValidator;
import ru.aston.banktest.dto.output.AccountOutputDto;


@RestController
@RequestMapping("/api/v1/account")
@Tag(name = "Account", description = "Контроллер создания и получения информации аккаунта")
public class AccountController {

    private final AccountCreateUseCase accountCreateUseCase;
    private final AccountGetUseCase accountGetUseCase;

    public AccountController(AccountCreateUseCase accountCreateUseCase, AccountGetUseCase accountGetUseCase) {
        this.accountCreateUseCase = accountCreateUseCase;
        this.accountGetUseCase = accountGetUseCase;
    }

    @PutMapping("/create")
    @Operation(summary = "Создание пользователя и счета", description = "Если пользователь существует создается новый счет при условии, что введеный пин-код не использовался")
    public void create(@RequestBody AccountInputDto accountInputDto) {
        AppValidator.validatePin(accountInputDto.pinCode());
        accountCreateUseCase.createAccount(accountInputDto.username(), accountInputDto.pinCode());
    }

    @GetMapping("/info")
    @Operation(summary = "Запрос информации о пользователе", description = "Получение информации о счетах пользователя")
    public AccountOutputDto getAccountInfo(@RequestParam(name = "username") String username) {
        return accountGetUseCase.getAccountInfo(username);
    }

    @GetMapping("")
    @Operation(summary = "Запрос информации о всех пользователях", description = "Получение информации о счетах пользователей")
    public Page<AccountOutputDto> getAllAccountInfo(@RequestParam(name = "page", defaultValue = "1") Integer page,
                                                    @RequestParam(name = "elements_count", defaultValue = "10") Integer elementsCount
    ) {
        return accountGetUseCase.getAllAccountInfo(page, elementsCount);
    }
}
