package com.wh.edu.eduservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@MapperScan("com.wh.edu.eduservice.mapper")
@EnableEurekaClient
@EnableFeignClients//发现注册中心的其他服务
public class EduserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EduserviceApplication.class, args);
    }

}
