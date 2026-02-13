package br.com.personalfinace.personalfinanceapi.business.tag.customs;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class TagRepositoryImpl implements TagRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

}
