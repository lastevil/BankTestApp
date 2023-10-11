package ru.aston.banktest.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.aston.banktest.core.entity.Bill;

import java.util.Optional;

public interface BillRepository extends JpaRepository<Bill, Long> {
    Optional<Bill> findByBillNumber(String billNumber);
}
