package com.hcq.seckill.web;

import com.hcq.seckill.keygen.KeyGenerator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class KeygenController {
    @Resource
    private KeyGenerator keyGenerator;

    @GetMapping("/keygen")
    public String generateKey() {
        return String.valueOf(keyGenerator.generateKey().longValue());
    }
}
