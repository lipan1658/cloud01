package org.example.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "CLIENT")
public interface FeignService {

    @GetMapping("/hello/{name}")
    public String hello(@PathVariable(value = "name") String name);
}
