package com.wh.edu.edustatic;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@MapperScan("com.wh.edu.edustatic.mapper")
@EnableFeignClients
public class EdustaticApplication {

    public static void main(String[] args) {
        SpringApplication.run(EdustaticApplication.class, args);
    }

}
