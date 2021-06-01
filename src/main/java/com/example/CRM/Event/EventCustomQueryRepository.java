package com.example.CRM.Event;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

@Repository
@Transactional
public class EventCustomQueryRepository {
    @PersistenceContext
    private EntityManager entityManager;
    public void deleteEvent(int eventId){
        Query query=  entityManager.createQuery("delete from event_store where eventID=:eventId");
        query.setParameter("eventId",eventId);
        query.executeUpdate();
    }
}
