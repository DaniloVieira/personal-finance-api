package br.com.personalfinace.personalfinanceapi.business.transaction;

import br.com.personalfinace.personalfinanceapi.business.transaction.customs.TransactionRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long>, TransactionRepositoryCustom {
    
    
    
}
