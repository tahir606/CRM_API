package com.example.CRM.Email.EmailList;

import com.example.CRM.Product.ProductModule;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@Repository
@Transactional
public class EmailListCustomQuery {
    @PersistenceContext
    private EntityManager entityManager;
    public int updateEmailList(EmailList emailList){
        Query query = entityManager.createQuery("update email_list set clientID=:clientId where emailID=:emailId");
        query.setParameter("emailId", emailList.getEmailID());
        query.setParameter("clientId", emailList.getClientID());

        return query.executeUpdate();
    }

    public List<String> getEmailAddress() {
      return  entityManager.createQuery("select address  from email_list ").getResultList();

    }

    public void deleteEmailListRow(int code) {
        Query query = entityManager.createQuery("delete from email_list where emailID = :code");
        query.setParameter("code",code);
        query.executeUpdate();

    }

}
