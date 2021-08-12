package com.example.yymes.controller;


import com.example.yymes.entity.User;
import com.example.yymes.mapper.userMapper;
import com.example.yymes.service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    userService userservice;
    @Autowired
    userMapper usermapper;

    @RequestMapping(value = "/username")
    @ResponseBody
    public List getname(){
        List<User> i = userservice.getUserById();
        return i;
    }
    /**
     * 新增
     * **/
    @RequestMapping(value = "/useradd")
    public String getnameadd(@RequestBody Map<String,Object> params){
        //String id = params.get("id").toString();
        String name = params.get("name").toString();
        List<User> i2 = userservice.getUserById();
        User i3 = i2.stream().max(Comparator.comparing(User::getId)).get();
        int i4 =  Integer.parseInt(i3.getId())+1;
        User u =new User();
        u.setId(String.valueOf(i4));
        u.setName(name);
        int i = usermapper.insert(u);

        return "添加成功";
    }
    /**
     * 删除
     * **/
    @RequestMapping(value = "/userdelete")
    public String getnamedelete(@RequestBody Map<String,Object> params){
        String id = params.get("id").toString();
        User u = new User();
        u.setId(id);
        userservice.getUserDeleteId(u);

        return "删除成功";
    }
    /**
     * 编辑
     * **/
    @RequestMapping(value = "/useredit")
    public String getnameidit(@RequestBody Map<String,Object> params){
        String id = params.get("id").toString();
        String name = params.get("name").toString();
        User u = new User();
        u.setId(id);
        u.setName(name);
        usermapper.updateById(u);
        return "编辑成功";
    }
}
