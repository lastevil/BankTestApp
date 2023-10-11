package ru.aston.banktest.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.aston.banktest.core.entity.Account;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByUsername(String username);
}
