package com.lizhuopeng;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(value ="com.lizhuopeng.dao")
public class UsersCenterMain {
    public static void main(String[] args) {
        SpringApplication.run(UsersCenterMain.class,args);
    }
}
