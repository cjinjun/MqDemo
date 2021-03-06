package com.rabbitmq.mq.work;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Description: work 消费者
 * @Author JASON
 * @Date 2019/08/13
 * @Version 1.0
 */
@Component
@Slf4j
public class WorkConsumer2 {


    private static final String QUEUE_NAME = "MQ.work";//定义一个队列名称

    @RabbitListener(queues = QUEUE_NAME)
    public void consume(Message message) {
        try {
            Thread.sleep(100L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("Work-Model Consumers2 receive information: {}" , new String(message.getBody()));
    }
}
