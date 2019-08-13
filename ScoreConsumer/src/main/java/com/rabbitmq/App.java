package com.rabbitmq;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * score consumer
 *
 */
@SpringBootApplication
@MapperScan("com.rabbitmq.mapper")
public class App {
    public static void main(String[] args) {
        System.out.println("SocreConsumer APP start...");
        SpringApplication.run(App.class, args);
    }
}