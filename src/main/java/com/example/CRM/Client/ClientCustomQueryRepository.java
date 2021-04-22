package com.example.CRM.Client;

import com.example.CRM.User.Users;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public class ClientCustomQueryRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public List<Client> emailsPerClient(String filter) {
        Query query = entityManager.createQuery("select new client_store (cs.clientID,cs.name,cs.owner, ( select count(distinct e.ticketNo ) from email_ticket e,email_list el where cs.clientID=el.clientID and  e.fromAddress LIKE CONCAT(" + "'%',el.address,'%'" + ") " + filter + "  )) From client_store cs " );
        return (List<Client>) query.getResultList();
    }
}
