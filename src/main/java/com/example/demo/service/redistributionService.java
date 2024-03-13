package com.example.demo.service;

import com.example.demo.dao.interfaces.RequestEntityRepository;
import com.example.demo.dao.interfaces.UserEntityRepository;
import com.example.demo.dao.model.Request;
import com.example.demo.dao.model.User;
import com.example.demo.enums.UserStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class redistributionService {
    private final UserEntityRepository userEntityRepository;
    private final RequestEntityRepository requestEntityRepository;

    public redistributionService(UserEntityRepository userEntityRepository,
                                 RequestEntityRepository requestEntityRepository) {
        this.userEntityRepository = userEntityRepository;
        this.requestEntityRepository = requestEntityRepository;
    }

    public void redistribution (User user){
        if(UserStatus.OFFLINE == user.getStatus()) {
            List<Request> requests = requestEntityRepository.getOpenedByUser(user.getObjectId());


        }
    }
}
