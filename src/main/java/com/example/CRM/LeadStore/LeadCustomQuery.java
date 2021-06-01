package com.example.CRM.LeadStore;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

@Repository
@Transactional
public class LeadCustomQuery {
    @PersistenceContext
    EntityManager entityManager;
    public void updateLead(int leadId){
        Query query=  entityManager.createQuery("update leads_store set converted =1 where leadsId=:leadId");
        query.setParameter("leadId",leadId);
        query.executeUpdate();
    }
}
