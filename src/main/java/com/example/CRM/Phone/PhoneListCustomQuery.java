package com.example.CRM.Phone;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

@Repository
@Transactional
public class PhoneListCustomQuery {
    @PersistenceContext
    private EntityManager entityManager;

    public void deletePhoneListRow(int code) {
        Query query = entityManager.createQuery("delete from phone_list where phoneID = :code");
        query.setParameter("code",code);
        query.executeUpdate();

    }
}
