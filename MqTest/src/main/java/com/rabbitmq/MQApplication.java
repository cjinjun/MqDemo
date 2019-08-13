package com.rabbitmq;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * order producer
 *
 */
@SpringBootApplication
@MapperScan("com.rabbitmq.mapper")
@EnableRabbit
public class MQApplication {
    public static void main( String[] args ){
        SpringApplication.run(MQApplication.class, args);
    }
}
