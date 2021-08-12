package com.example.yymes;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.example.yymes.mapper")
@SpringBootApplication
public class YymesApplication {

    public static void main(String[] args) {
        SpringApplication.run(YymesApplication.class, args);
    }

}
