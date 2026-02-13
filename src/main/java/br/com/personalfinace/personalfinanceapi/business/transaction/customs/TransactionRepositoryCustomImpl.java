package br.com.personalfinace.personalfinanceapi.business.transaction.customs;

import br.com.personalfinace.personalfinanceapi.business.transaction.Transaction;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TransactionRepositoryCustomImpl implements  TransactionRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @SuppressWarnings("unchecked")
    public List<Transaction> findAllByTagIncludingSubtags(Long tagId) {
        return entityManager.createNativeQuery("""
        WITH RECURSIVE subtags AS (
            SELECT id_tag FROM pfi_tag WHERE id_tag = :tagId
            UNION ALL
            SELECT t.id_tag FROM pfi_tag t
            JOIN subtags s ON t.fk_parent_tag = s.id_tag
        )
        SELECT tr.* FROM pfi_transaction tr
        JOIN pfi_transaction_tag tt ON tr.id_transaction = tt.fk_transaction
        WHERE tt.fk_tag IN (SELECT id_tag FROM subtags)
        """, Transaction.class)
                .setParameter("tagId", tagId)
                .getResultList();
    }

}
