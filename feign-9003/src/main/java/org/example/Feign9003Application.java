package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class Feign9003Application {
    public static void main(String[] args) {
        SpringApplication.run(Feign9003Application.class,args);
    }

}
