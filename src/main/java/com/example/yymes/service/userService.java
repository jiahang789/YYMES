package com.example.yymes.service;


import com.example.yymes.entity.User;

import java.util.List;

public interface userService {

   List<User> getUserById();

   int getUserDeleteId(User id);
}
