package com.example.CRM.Email.Setiings;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

@Repository
@Transactional
public class SettingsCustomQuery {
    @PersistenceContext
    private EntityManager entityManager;


    public void updateReplacementKeyword(String keyword) {
        Query query = entityManager.createQuery("UPDATE email_settings set replacementKeyword = :keyword " );
        query.setParameter("keyword" ,keyword);
        query.executeUpdate();
    }
}
