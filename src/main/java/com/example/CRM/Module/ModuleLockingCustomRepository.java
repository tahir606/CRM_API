package com.example.CRM.Module;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

@Transactional
@Repository
public class ModuleLockingCustomRepository {

    @PersistenceContext
    EntityManager entityManager;

    public int updateModuleLocking(ModuleLocking moduleLocking) {
        Query query = entityManager.createQuery("update module_locking set state=0,moduleId=:moduleId, pmID =:pmId, description=: description ,createdBy=:createdBy ,unLockedTime=:unlockedTime where moduleId=:moduleId and pmID=:pmId");
        query.setParameter("pmId", moduleLocking.getPmID());
        query.setParameter("moduleId", moduleLocking.getModuleId());
        query.setParameter("description", moduleLocking.getDescription());
        query.setParameter("createdBy", moduleLocking.getCreatedBy());
        query.setParameter("unlockedTime", moduleLocking.getUnLockedTime());
        return query.executeUpdate();
    }
}
