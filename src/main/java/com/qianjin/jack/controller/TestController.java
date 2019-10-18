package com.qianjin.jack.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author volume
 * @date 2019/10/18 10:15
 */
@RestController
public class TestController {
    @Value("${version}")
    private String version;

    @GetMapping("/")
    public String test() {
        return version;
    }
}
