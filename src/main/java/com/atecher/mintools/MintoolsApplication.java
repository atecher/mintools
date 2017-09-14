package com.atecher.mintools;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.atecher.mintools.mapper")
public class MintoolsApplication {

    public static void main(String[] args) {
        SpringApplication.run(MintoolsApplication.class, args);
    }
}
