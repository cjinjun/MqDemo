package com.rabbitmq.mq.transaction;

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
public class TransationProducer {

    @Autowired
    private AmqpTemplate amqpTemplate;




    private static final String QUEUE_NAME = "MQ.queue.transation.one";
    private static final String EXCHANGE_NAME = "MQ.transation";
    private static final String TRANSATION_ROUTE_KEY = "MQ.transation.route.key";

    /**
     * 创建QUEUE
     *
     * @return
     */
    @Bean
    public Queue transationQueueOne() {
        return new Queue(QUEUE_NAME, true);
    }


    /**
     * transation exchange
     *
     * @return
     */
    @Bean
    public TopicExchange transationExchange() {
        return new TopicExchange(EXCHANGE_NAME);
    }

    /**
     * queue 与 交换机绑定  指定路由KEY1
     * 星号 表示 one word
     * @return
     */
    @Bean
    public Binding bindingTopicExchangeWithQueue() {
        return BindingBuilder.bind(transationQueueOne()).to(transationExchange()).with(TRANSATION_ROUTE_KEY);
    }


}
