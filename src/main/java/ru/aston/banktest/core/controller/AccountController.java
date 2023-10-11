package ru.aston.banktest.core.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import ru.aston.banktest.contract.account.input.AccountCreateUseCase;
import ru.aston.banktest.contract.account.input.AccountGetUseCase;
import ru.aston.banktest.dto.input.account.AccountInputDto;
import ru.aston.banktest.dto.input.validation.PinValidator;
import ru.aston.banktest.dto.output.AccountOutputDto;


@RestController
@RequestMapping("/account")
@Tag(name = "Account", description = "Контроллер создания и получения информации аккаунта")
public class AccountController {

    private final AccountCreateUseCase accountCreateUseCase;
    private final AccountGetUseCase accountGetUseCase;

    public AccountController(AccountCreateUseCase accountCreateUseCase, AccountGetUseCase accountGetUseCase) {
        this.accountCreateUseCase = accountCreateUseCase;
        this.accountGetUseCase = accountGetUseCase;
    }

    @PutMapping("/create")
    public void create(@RequestBody AccountInputDto accountInputDto) {
        PinValidator.isValid(accountInputDto.pinCode());
        accountCreateUseCase.createAccount(accountInputDto.username(), accountInputDto.pinCode());
    }

    @GetMapping("/info")
    public AccountOutputDto getAccountInfo(@RequestParam(name = "username") String username) {
        return accountGetUseCase.getAccountInfo(username);
    }
}
