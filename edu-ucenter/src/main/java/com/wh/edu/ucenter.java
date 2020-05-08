package com.wh.edu;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@MapperScan("com.wh.edu.mapper")
public class ucenter {
    public static void main(String[] args){
        SpringApplication.run(ucenter.class, args);}
}
