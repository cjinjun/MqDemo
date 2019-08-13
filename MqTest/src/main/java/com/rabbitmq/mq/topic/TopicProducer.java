package com.rabbitmq.mq.topic;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @Description: topic 模式
 * @Author Jason
 * @Date 2019/08/13
 * @Version 1.0
 */
@Component
@Slf4j
public class TopicProducer {

    @Autowired
    private AmqpTemplate amqpTemplate;


    /**
     * 多个词匹配
     */
    public void sendTopicN() {
        for (int i = 0; i < 20; i++) {
            String message = "Topic N mq " + i;
            //第一个参数为exchangeName,第二个是topic key，第三个是发送内容
            amqpTemplate.convertAndSend(EXCHANGE_NAME, "F.C.E", message);
        }
        log.warn("Topic-Model send success");
    }

    /**
     * 一个词匹配
     */
    public void sendTopic1() {
        for (int i = 20; i < 50; i++) {
            String message = "Topic1 mq " + i;
            //第一个参数为exchangeName,第二个是topic key，第三个是发送内容
            amqpTemplate.convertAndSend(EXCHANGE_NAME, "A.F", message);
        }
        log.warn("Topic-Model send success");
    }


    private static final String QUEUE_NAME = "mq.queue.topic.one";
    private static final String QUEUE_NAME_2 = "mq.queue.topic.two";
    private static final String EXCHANGE_NAME = "mq.topic";
    private static final String ROUTING_KEY_1 = "*.C.*";
    private static final String ROUTING_KEY_2 = "A.*.*";

    /**
     * 创建QUEUE
     *
     * @return
     */
    @Bean
    public Queue topicQueueOne() {
        return new Queue(QUEUE_NAME, true);
    }

    @Bean
    public Queue topicQueueTwo() {
        return new Queue(QUEUE_NAME_2, true);
    }

    /**
     * topic exchange
     *
     * @return
     */
    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(EXCHANGE_NAME);
    }

    /**
     * queue 与 交换机绑定  指定路由KEY1
     * 星号 表示 one word
     * @return
     */
    @Bean
    public Binding bindingTopicExchangeWithQueue() {
        return BindingBuilder.bind(topicQueueOne()).to(topicExchange()).with(ROUTING_KEY_1);
    }

    /**
     * queue2 与 交换机绑定 指定路由KEY2
     * # 号 表示 more word
     * @return
     */
    @Bean
    public Binding bindingTopicExchangeWithQueue2() {
        return BindingBuilder.bind(topicQueueTwo()).to(topicExchange()).with(ROUTING_KEY_2);
    }

}
