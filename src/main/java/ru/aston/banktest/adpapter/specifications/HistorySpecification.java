package ru.aston.banktest.adpapter.specifications;

import org.springframework.data.jpa.domain.Specification;
import ru.aston.banktest.core.entity.History;

import java.time.LocalDateTime;

public class HistorySpecification {
    private HistorySpecification(){}
    public static Specification<History> dateAfterThat(LocalDateTime time) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("createdAt"), time));
    }

    public static Specification<History> dateBeforeThat(LocalDateTime time) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("createdAt"), time));
    }

    public static Specification<History> whereBill(String bill) {
        Specification<History> history1 = ((root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("fromBill").get("billNumber"), bill));
        Specification<History> history2 = ((root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("toBill").get("billNumber"), bill));
        return history1.or(history2);
    }
}
