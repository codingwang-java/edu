package com.dejavu.edu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author dejavu
 * @description 启动类
 * @create 2021-02-19 12:46
 **/
@SpringBootApplication
@EnableSwagger2
public class ServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceApplication.class,args);
    }
}
