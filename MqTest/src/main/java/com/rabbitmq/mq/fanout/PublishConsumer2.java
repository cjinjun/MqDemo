package com.rabbitmq.mq.fanout;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Description: 发布/订阅 消费者
 * @Author Jason
 * @Date 2019/08/13
 * @Version 1.0
 */
@Component
@Slf4j
public class PublishConsumer2 {

    private static final String QUEUE_NAME_2 = "MQ.publish.two";

    @RabbitListener(queuesToDeclare = @org.springframework.amqp.rabbit.annotation.Queue(QUEUE_NAME_2))
    public void consume(Message message) {
        try {
            Thread.sleep(100L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("Publish-Model Consumers2 receive information: {}" , new String(message.getBody()));
    }
}
