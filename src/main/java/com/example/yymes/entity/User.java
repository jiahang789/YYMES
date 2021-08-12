package com.example.yymes.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("student")
public class User {
    private String Id;
    private String Name;


}
