package org.example.controller;

import org.example.service.FeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FeignHystrixController {

    @Autowired
    private FeignService feignService;

    @GetMapping("/hello/{name}")
    public String hello(@PathVariable String name){
        return feignService.hello(name);
    }
}
