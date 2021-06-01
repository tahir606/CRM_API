package com.example.CRM.Email.EmailTicket;

import com.example.CRM.User.Users;
import org.hibernate.mapping.Collection;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.transaction.Transactional;
import javax.validation.constraints.Email;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
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

    public List<EmailTickets> archiveEmails(String email) {
        return (List<EmailTickets>) entityManager.createQuery("select e from email_ticket e where " + email)
                .getResultList();
    }

    public List<EmailTickets> getNewEmails(int email) {
        return (List<EmailTickets>) entityManager.createQuery("select e from email_ticket e where e.ticketNo > " + email)
                .getResultList();
    }

    public List<EmailTickets> ticketsSolvedByUserDetails(int userCode, String filter) {
        Query query = entityManager.createQuery("SELECT new email_ticket(e.ticketNo ,e.code,  e.subject,e.fromAddress,e.timestamp,  h.time as lockedTime, (SELECT h1.time from history h1 where h1.status =3 and  h1.emailId=e.code  and h1.userId =:userCode ) as solvedTime ) FROM email_ticket e, history h  where h.status =2 and  h.emailId=e.code and h.userId =:userCode " + filter);
        query.setParameter("userCode", userCode);
        return query.getResultList();
    }

    public List<EmailTickets> clientReportWithDomain(int clientId,String filter) {
        String sql = "SELECT e.ticketNo,e.email_no, e.subject, e.from_address,e.timestamp ,(SELECT h.time from history h where h.status=2 and e.email_no =h.emailId ORDER by h.hCode DESC LIMIT 1),(SELECT h.time from history h where h.status=3 and e.email_no =h.emailId  ORDER by h.hCode DESC LIMIT 1) FROM email_ticket e  WHERE e.freeze=0  AND  SUBSTRING_INDEX(SUBSTRING_INDEX(e.from_address,'>',1),'<',-1)  IN (Select el.Email_Address FROM email_list el WHERE el.CL_ID ="+clientId+" and el.CL_ID!=0 "+filter+" GROUP BY el.Email_Address)";
        Query query = entityManager.createNativeQuery(sql);
        List<Object[]> authors = query.getResultList();
        List<EmailTickets> emailTickets = new ArrayList<>();
        for (Object[] a : authors) {

            emailTickets.add(new EmailTickets((Integer) a[0],(Integer) a[1], String.valueOf(a[2]), Collections.singletonList(String.valueOf(a[3])), (Date)a[4],(Date)a[5], (Date) a[6]));
        }
        return emailTickets;
    }

    public int getSolvedOrLockedEmails(int status, int userId) {
        Query query = entityManager.createQuery("SELECT COUNT(DISTINCT h.emailId) FROM history h,email_ticket e WHERE h.userId = " + userId + " AND h.emailId = e.code AND h.status = " + status + " AND e.status = " + status + "AND e.freeze =" + 0);
        return ((Number) query.getSingleResult()).intValue();
    }

    public int getOfUnLockedEmails() {
        Query query = entityManager.createQuery("SELECT COUNT(DISTINCT e.code) FROM email_ticket e WHERE  e.status = " + 1 + " AND e.freeze =" + 0);
        return ((Number) query.getSingleResult()).intValue();
    }

    public int getOfUnSolvedEmails() {
        Query query = entityManager.createQuery("SELECT COUNT(DISTINCT e.code) FROM email_ticket e WHERE  e.status != " + 3 + " AND e.status = " + 2 + " AND e.freeze =" + 0);
        return ((Number) query.getSingleResult()).intValue();
    }
    public int getMaxTicketNo() {
        Query query = entityManager.createQuery("SELECT MAX (e.ticketNo) FROM email_ticket e WHERE  e.freeze =" + 0);
        return ((Number) query.getSingleResult()).intValue();
    }
}
