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
@NoArgsConstructor
@Table(name = "history")
@Getter
@Setter
public class History {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(name = "action")
    @Enumerated(EnumType.STRING)
    Actions action;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "from_bill")
    Bill fromBill;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "to_bill")
    Bill toBill;
    @CreationTimestamp
    @Column(name = "created_at")
    ZonedDateTime createdAt;
    @Column(name = "amount")
    BigDecimal amount;

    public History(Actions actions, Bill fromBill, Bill toBill, BigDecimal sum) {
        this.action = actions;
        this.fromBill = fromBill;
        this.toBill = toBill;
        this.amount = sum;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        History history = (History) o;
        return Objects.equals(id, history.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
