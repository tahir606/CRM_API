package com.example.CRM.Rights.RightsChart;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transaction;
import javax.transaction.Transactional;

@Repository
@Transactional
public class RightsChartCustomQueryRepository {
    @PersistenceContext
    private EntityManager entityManager;
    public void deleteRights(int userCode){
       Query query= entityManager.createQuery(" delete from rights_chart r where r.userCode = "+userCode );
       query.executeUpdate();
    }

}
