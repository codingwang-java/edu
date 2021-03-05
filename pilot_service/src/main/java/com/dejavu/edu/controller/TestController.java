package com.dejavu.edu.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author dejavu
 * @description
 * @create 2021-02-19 13:24
 **/
@RestController
@RequestMapping("/test")
@Slf4j
public class TestController {
    @RequestMapping("/test")
    public String test() throws ClassNotFoundException {
        log.info("tesController");
        return "hello";
    }
}
