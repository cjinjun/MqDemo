package com.rabbitmq.mq.hello;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Description: hello
 * @Author Jason
 * @Date 2019/08/13
 * @Version 1.0
 */
@Component
@Slf4j
public class HelloProducer {

    @Autowired
    private AmqpTemplate amqpTemplate;

    /**
     * 定义一个队列名称
     */
    private static final String QUEUE_NAME = "MQ.hello";

    public void send() {
        String message = "Hello mq TEST";
        amqpTemplate.convertAndSend(QUEUE_NAME, message);
        log.warn("Hello-Model send success");
    }

}
