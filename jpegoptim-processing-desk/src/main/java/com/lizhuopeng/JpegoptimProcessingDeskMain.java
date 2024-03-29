package com.lizhuopeng;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class JpegoptimProcessingDeskMain {
    public static void main(String[] args) {
        SpringApplication.run(JpegoptimProcessingDeskMain.class,args);
    }
}
