package com.rabbitmq.mq.transaction;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @Description: topic 模式 消费者
 * @Author Jason
 * @Date 2019/08/13
 * @Version 1.0
 */
@Component
@Slf4j
public class TopicConsumer {

    private static final String QUEUE_NAME = "MQ.queue.topic.one";

    @RabbitListener(queues = QUEUE_NAME)
    public void consume(Message message, Channel channel) throws IOException {
//        channel.basicQos(0, 3, false);
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
        log.warn("Topic-Model Consumers1 receive information: {}", new String(message.getBody()));
    }
}
