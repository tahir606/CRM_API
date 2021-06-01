package com.example.CRM.Product;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

@Transactional
@Repository
public class ProductModuleCustomQuery {
    @PersistenceContext
    private EntityManager entityManager;

    public int deleteProductModule(int productId) {
        Query query = entityManager.createQuery("delete from product_module where psID=:productId");
        query.setParameter("productId", productId);
        return query.executeUpdate();


    }

    public int updateProductModule(ProductModule productModule) {
        Query query = entityManager.createQuery("update product_module set pmID =:pmId, psID =:psId ,name=: name ,description=: description ,createdBy=:createdBy ,createdOn=:createdOne where psID=:psId and pmID=:pmId");
        query.setParameter("pmId", productModule.getPmID());
        query.setParameter("psId", productModule.getPsID());
        query.setParameter("name", productModule.getName());
        query.setParameter("description", productModule.getDescription());
        query.setParameter("createdBy", productModule.getCreatedBy());
        query.setParameter("createdOne", productModule.getCreatedOn());
        return query.executeUpdate();
    }
}
