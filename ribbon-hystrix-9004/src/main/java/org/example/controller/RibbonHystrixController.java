package org.example.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class RibbonHystrixController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/hello/{name}")
    @HystrixCommand(fallbackMethod = "helloError")
    public String hello(@PathVariable String name){
        return restTemplate.getForObject("http://client/hello/"+name,String.class);
    }

    public String helloError(String name){
        return "helloError,"+name;
    }
}
