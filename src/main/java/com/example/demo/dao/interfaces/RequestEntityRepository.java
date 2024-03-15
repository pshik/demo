package com.example.demo.dao.interfaces;

import com.example.demo.dao.model.Request;

import java.util.List;

public interface RequestEntityRepository {
    List<Request> getAll();
    Request find(String objectId);
    void delete(Request entity);
    void save(Request entity);
    List<Request> getOpenedByUser(String userId);
    List<Request> getOpenedByUsers(List<String> ids);

}
