package com.hcq.seckill.rest;

import com.hcq.seckill.keygen.KeyGenerator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("keygen")
public class KeygenController {
    @Resource
    private KeyGenerator keyGenerator;

    @GetMapping("generateKey")
    public String generateKey() {
        Long id = keyGenerator.generateKey().longValue();
        System.out.println(id);
        return String.valueOf(id);
    }
}
