package com.wh.edu;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class ekserviceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ekserviceApplication.class,args);
    }
}
