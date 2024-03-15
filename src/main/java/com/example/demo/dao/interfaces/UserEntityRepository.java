package com.example.demo.dao.interfaces;

import com.example.demo.dao.model.User;

import java.util.List;

public interface UserEntityRepository {
    List<User> getAll();
    User find(String objectId);
    void delete(User entity);

    void save(User entity);
    List<User> getOnlineUsers();
}
