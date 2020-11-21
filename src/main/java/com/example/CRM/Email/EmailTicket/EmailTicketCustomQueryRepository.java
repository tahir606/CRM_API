package com.example.CRM.Email.EmailTicket;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class EmailTicketCustomQueryRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public List<EmailTickets> getFilterEmail(String email) {
        return (List<EmailTickets>) entityManager.createQuery("select e from email_ticket e  where " + email)
                .getResultList();
    }

    public List<EmailTickets>  archiveEmails(String email) {
        return (List<EmailTickets>)  entityManager.createQuery("select e from email_ticket e where " + email)
                .getResultList();
    }

}
