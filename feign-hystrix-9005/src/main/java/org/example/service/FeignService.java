package org.example.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "CLIENT",fallback = FeignServiceFallback.class)
public interface FeignService {
    @GetMapping("/hello/{name}")
    public String hello(@PathVariable("name") String name);
}
