package com.example.demo.dao.jpa;

import com.example.demo.dao.interfaces.RequestEntityRepository;
import com.example.demo.dao.model.Request;
import com.example.demo.dao.model.Request_;
import com.example.demo.dao.model.User_;
import com.example.demo.enums.RequestStatus;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RequestEntityRepositoryImpl implements RequestEntityRepository {
    @PersistenceContext
    protected EntityManager entityManager;

    @Override
    public List<Request> getAll() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Request> query = builder.createQuery(Request.class);
        Root<Request> root = query.from(Request.class);
        CriteriaQuery<Request> select = query.select(root);
        return entityManager.createQuery(select).getResultList();
    }

    @Override
    public Request find(String objectId) {
        return entityManager.find(Request.class,objectId);
    }

    @Override
    public void delete(Request entity) {
        entityManager.remove(entity);
    }


    @Override
    public void save(Request entity) {
        if(entity.getObjectId() == null){
            entityManager.persist(entity);
        } else {
            entityManager.merge(entity);
        }
    }
    @Override
    public List<Request> getOpenedByUser(String userId) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Request> query = builder.createQuery(Request.class);
        Root<Request> root = query.from(Request.class);
        CriteriaQuery<Request> select = query.select(root);

        select.where(builder.and(
             builder.equal(root.get(Request_.user), userId),
             builder.equal(root.get(Request_.status), RequestStatus.OPEN)
        ));

        return entityManager.createQuery(select).getResultList();
   }
   @Override
    public List<Request> getOpenedByUsers(List<String> ids) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Request> query = builder.createQuery(Request.class);
        Root<Request> root = query.from(Request.class);

        CriteriaQuery<Request> select = query.select(root);

        if(ids != null && !ids.isEmpty()){
            select.where(builder.and(
                 root.get(Request_.user).get(User_.objectId).in(ids),
                 builder.equal(root.get(Request_.status), RequestStatus.OPEN)
            ));
        }

        return entityManager.createQuery(select).getResultList();
   }
}
