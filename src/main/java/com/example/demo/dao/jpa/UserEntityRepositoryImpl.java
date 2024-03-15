package com.example.demo.dao.jpa;

import com.example.demo.dao.interfaces.UserEntityRepository;
import com.example.demo.dao.model.User;
import com.example.demo.dao.model.User_;
import com.example.demo.enums.UserStatus;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class UserEntityRepositoryImpl implements UserEntityRepository {


    @PersistenceContext
    protected EntityManager entityManager;

    @Override
    public List<User> getAll() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> query = builder.createQuery(User.class);
        Root<User> root = query.from(User.class);
        CriteriaQuery<User> select = query.select(root);
        return entityManager.createQuery(select).getResultList();
    }

    @Override
    public User find(String objectId) {
        return entityManager.find(User.class,objectId);
    }

    @Override
    public void delete(User entity) {
        entityManager.remove(entity);
    }

    @Override
    public void save(User entity) {
        if(entity.getObjectId() == null){
            entityManager.persist(entity);
        } else {
            entityManager.merge(entity);
        }
    }

    @Override
    public List<User> getOnlineUsers() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> query = builder.createQuery(User.class);
        Root<User> root = query.from(User.class);
        CriteriaQuery<User> select = query.select(root);

        select.where(builder.equal(root.get(User_.STATUS), UserStatus.ONLINE));

        return entityManager.createQuery(select).getResultList();
    }
}
