package com.example.yymes.serviceImpl;


import com.example.yymes.entity.User;
import com.example.yymes.mapper.userMapper;
import com.example.yymes.service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class userServiceImpl implements userService {
    @Autowired
    userMapper usermapper;


    @Override
    public List<User> getUserById() {
        return usermapper.getUserById();
    }

    @Override
    public int getUserDeleteId(User id) {
        return usermapper.getUserDeleteId(id);
    }
}
