package com.tian;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.tian.dao")
public class SsmpApplication {

    public static void main(String[] args) {
        SpringApplication.run(SsmpApplication.class, args);
    }

}
