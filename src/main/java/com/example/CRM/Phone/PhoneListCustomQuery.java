package com.example.CRM.Phone;

import com.example.CRM.Email.EmailList.EmailList;
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
    public int updatePhoneList(PhoneList phoneList){
        Query query = entityManager.createQuery("update phone_list set clientID=:clientId where phoneID=:phoneId");
        query.setParameter("phoneId", phoneList.getPhoneID());
        query.setParameter("clientId", phoneList.getClientID());

        return query.executeUpdate();
    }
}
