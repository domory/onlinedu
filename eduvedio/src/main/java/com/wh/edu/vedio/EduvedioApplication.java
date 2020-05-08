package com.wh.edu.vedio;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class EduvedioApplication {
    public static void main(String[] args){
        SpringApplication.run(EduvedioApplication.class, args);
    }
}
