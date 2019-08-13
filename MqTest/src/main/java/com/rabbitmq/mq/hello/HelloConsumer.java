package com.rabbitmq.mq.hello;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Description: hello 消费者
 * @Author Jason
 * @Date 2019/08/13
 * @Version 1.0
 */
@Component
@Slf4j
public class HelloConsumer {


    private static final String QUEUE_NAME = "MQ.hello";//定义一个队列名称


    @RabbitListener(queuesToDeclare = @org.springframework.amqp.rabbit.annotation.Queue(QUEUE_NAME))
    public void consume(Message message) {
        log.warn("Hello-Model Consumers receive information: {}" , new String(message.getBody()));
    }
}
