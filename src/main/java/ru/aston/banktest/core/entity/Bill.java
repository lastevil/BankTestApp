package ru.aston.banktest.core.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Objects;

@Entity
@Table(name = "bills")
@NoArgsConstructor
@Getter
@Setter
public class Bill {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @JoinColumn(name = "account_id")
    @ManyToOne(fetch = FetchType.LAZY)
    Account account;
    @Column(name = "pin_code")
    Integer pinCode;
    @Column(name = "bill_number")
    String billNumber;
    @Column(name = "balance")
    BigDecimal balance;
    @CreationTimestamp
    @Column(name = "created_at")
    ZonedDateTime createdAt;

    public Bill(Account account, String pinCode, String billNumber) {
        setPinCode(pinCode);
        this.billNumber = billNumber;
        this.account = account;
        this.balance = BigDecimal.valueOf(0);
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode.hashCode();
    }

    public boolean validatePin(String pinCode) {
        return this.pinCode.equals(pinCode.hashCode());
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bill bill = (Bill) o;
        return Objects.equals(id, bill.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
