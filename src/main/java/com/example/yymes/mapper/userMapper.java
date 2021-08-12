package com.example.yymes.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.yymes.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface userMapper extends BaseMapper<User> {

    List<User> getUserById();

    int getUserDeleteId(User id);
}
