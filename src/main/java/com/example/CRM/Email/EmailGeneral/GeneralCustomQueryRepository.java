package com.example.CRM.Email.EmailGeneral;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

@Repository
@Transactional
public class GeneralCustomQueryRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public int getMaxGeneralNo() {
        Query query = entityManager.createQuery("SELECT MAX (e.code) FROM email_general e WHERE  e.freeze =" + 0);
        return ((Number) query.getSingleResult()).intValue();
    }
}
