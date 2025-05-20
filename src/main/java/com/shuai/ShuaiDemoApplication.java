package com.shuai;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.shuai.mapper")
public class ShuaiDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShuaiDemoApplication.class, args);
    }
}
