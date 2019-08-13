package com.rabbitmq.mq.header;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @Description: header 模式 消费者
 * @Author Jason
 * @Date 2019/08/13
 * @Version 1.0
 */
@Component
@Slf4j
public class HeaderAnyConsumer {

    private static final String QUEUE_NAME = "mq.queue.header.any";

    @RabbitListener(queuesToDeclare = @Queue(QUEUE_NAME))
    public void consume(Message message, Channel channel) throws IOException {
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
        log.warn("Header-Model match any receive information: {}", new String(message.getBody()));
    }
}
