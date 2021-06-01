package com.example.CRM.Task;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

@Repository
@Transactional
public class TaskCustomQueryRepository {
    @PersistenceContext
    EntityManager entityManager;
    public void deleteTask(int taskID){
        Query query=  entityManager.createQuery("delete from task_store where taskID=:taskID");
        query.setParameter("taskID",taskID);
        query.executeUpdate();
    }
}
