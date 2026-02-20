package br.com.personalfinace.personalfinanceapi.business.transaction;

import br.com.personalfinace.personalfinanceapi.business.transaction.customs.TransactionRepositoryCustom;
import br.com.personalfinace.personalfinanceapi.business.transaction.enums.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long>, TransactionRepositoryCustom {
    List<Transaction> findByParentTransactionId(Long id);
    List<Transaction> findByAccountUserId(Long userId);

    @Query("""
        SELECT COALESCE(SUM(t.amount), 0)
        FROM Transaction t
        WHERE t.type = :type
        AND t.parentTransaction IS NULL
    """)
    BigDecimal sumAmount(@Param("type") TransactionType type);

    @Query("""
        SELECT COALESCE(SUM(t.amount), 0)
        FROM Transaction t
        WHERE t.type = :type
        AND t.parentTransaction IS NULL
        AND t.date BETWEEN :start AND :end
    """)
    BigDecimal sumAmountByPeriod(@Param("type") TransactionType type, @Param("start") LocalDate start, @Param("end") LocalDate end);

}
