package com.example.mumuoa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
public class MumuOaApplication {

    public static void main(String[] args) {
        SpringApplication.run(MumuOaApplication.class, args);
    }

}