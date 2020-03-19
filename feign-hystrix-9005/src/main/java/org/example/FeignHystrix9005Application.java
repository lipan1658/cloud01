package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableHystrix
@EnableFeignClients
public class FeignHystrix9005Application {
    public static void main(String[] args) {
        SpringApplication.run(FeignHystrix9005Application.class,args);
    }
}
