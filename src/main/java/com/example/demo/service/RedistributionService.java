package com.example.demo.service;

import com.example.demo.dao.interfaces.RequestEntityRepository;
import com.example.demo.dao.interfaces.UserEntityRepository;
import com.example.demo.dao.model.Request;
import com.example.demo.dao.model.User;
import com.example.demo.enums.UserStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class RedistributionService {
    private final UserEntityRepository userEntityRepository;
    private final RequestEntityRepository requestEntityRepository;

    public RedistributionService(UserEntityRepository userEntityRepository,
                                 RequestEntityRepository requestEntityRepository) {
        this.userEntityRepository = userEntityRepository;
        this.requestEntityRepository = requestEntityRepository;
    }

    public void redistribution (User user){

        if(user != null && UserStatus.OFFLINE == user.getStatus()) {
            List<Request> requests = requestEntityRepository.getOpenedByUser(user.getObjectId());
            if(!(requests == null || !requests.isEmpty())) {
                List<User> users = userEntityRepository.getOnlineUsers();
                Map<User,Long> countRequestByUser = new HashMap<>();
                List<Request> requestsByUsersOnline = requestEntityRepository.getOpenedByUsers(
                        users.stream().map(User::getObjectId).collect(Collectors.toList()));
                requestsByUsersOnline.forEach(request -> {
                    if(countRequestByUser.containsKey(request.getUser())){
                        countRequestByUser.put(request.getUser(),
                                countRequestByUser.get(request.getUser())+1);

                    } else {
                        countRequestByUser.put(request.getUser(),1L);
                    }
                });
                List<Request> updatedRequests = redistributionRequests(requests, countRequestByUser);
                updatedRequests.forEach(requestEntityRepository::save);
            }
        }
    }

    private List<Request> redistributionRequests (List<Request> requests, Map<User, Long> onlineUsers){
        List<Request> result = new ArrayList<>();
        List<Request> source = new ArrayList<>(requests);
        while (!source.isEmpty()){
            Request request = source.stream().findFirst().orElse(null);
            if(onlineUsers.entrySet()
                    .stream().min(Map.Entry.comparingByValue()).isPresent()){
                Map.Entry<User, Long> userMap = onlineUsers.entrySet()
                        .stream().min(Map.Entry.comparingByValue()).get();
                request.setUser(userMap.getKey());
                result.add(request);
                onlineUsers.put(userMap.getKey(),userMap.getValue() + 1);
                source.remove(request);
            }
        }
        return result;
    }
}
