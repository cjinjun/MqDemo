package com.rabbitmq.mq.header;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: topic 模式
 * @Author Jason
 * @Date 2019/08/13
 * @Version 1.0
 */
@Component
@Slf4j
public class HeaderProducer {

    @Autowired
    private AmqpTemplate amqpTemplate;

    public void send_match_no() {
        MessageProperties messageProperties = new MessageProperties();
        // 设置消息是否持久化。Persistent表示持久化，Non-persistent表示不持久化
        messageProperties.setDeliveryMode(MessageDeliveryMode.NON_PERSISTENT);
        messageProperties.setContentType("UTF-8");
        messageProperties.setHeader("Header3", "XXX");
        Message message = new Message("Test rabbit headers match no".getBytes(), messageProperties);
        amqpTemplate.convertAndSend(EXCHANGE_NAME, null, message);
        log.warn("header-Model send success");
    }

    public void send_match_all() {
        MessageProperties messageProperties = new MessageProperties();
        // 设置消息是否持久化。Persistent表示持久化，Non-persistent表示不持久化
        messageProperties.setDeliveryMode(MessageDeliveryMode.NON_PERSISTENT);
        messageProperties.setContentType("UTF-8");
        messageProperties.setHeader("Header1", "YM");
        messageProperties.setHeader("Header2", "JR");
        Message message = new Message("Test rabbit headers match all！".getBytes(), messageProperties);
        amqpTemplate.convertAndSend(EXCHANGE_NAME, null, message);
        log.warn("header-Model send success");
    }

    public void send_match_any() {
        MessageProperties messageProperties = new MessageProperties();
        // 设置消息是否持久化。Persistent表示持久化，Non-persistent表示不持久化
        messageProperties.setDeliveryMode(MessageDeliveryMode.NON_PERSISTENT);
        messageProperties.setContentType("UTF-8");
        messageProperties.setHeader("Header1", "YM");
        Message message = new Message("Test rabbit headers match any".getBytes(), messageProperties);
        amqpTemplate.convertAndSend(EXCHANGE_NAME, null, message);
        log.warn("header-Model send success");
    }




    private static final String QUEUE_ALL_NAME = "mq.queue.header.all";
    private static final String QUEUE_ANY_NAME = "mq.queue.header.any";
    private static final String EXCHANGE_NAME = "mq.header";

    /**
     * 创建QUEUE
     *
     * @return
     */
    @Bean
    public Queue headerAllQueue() {
        return new Queue(QUEUE_ALL_NAME, true);
    }

    @Bean
    public Queue headerAnyQueue() {
        return new Queue(QUEUE_ANY_NAME, true);
    }


    /**
     * header exchange
     *
     * @return
     */
    @Bean
    public HeadersExchange headerExchange() {
        return new HeadersExchange(EXCHANGE_NAME);
    }

    /**
     * queue 与 交换机绑定
     * 星号 表示 one word
     * @return
     */
    @Bean
    public Binding bindingHeaderAllExchangeWithQueue() {
        Map<String,Object> headerMap = new HashMap<>();
        headerMap.put("Header1","YM");
        headerMap.put("Header2","JR");
//        whereAll表示全部匹配
        return BindingBuilder.bind(headerAllQueue()).to(headerExchange()).whereAll(headerMap).match();
    }

    /**
     * queue 与 交换机绑定  指定路由KEY2
     * 星号 表示 one word
     * @return
     */
    @Bean
    public Binding bindingHeaderAnyExchangeWithQueue() {
        Map<String,Object> headerMap = new HashMap<>();
        headerMap.put("Header1","YM");
        headerMap.put("Header2","JR");
        //where Any部分匹配
        return BindingBuilder.bind(headerAnyQueue()).to(headerExchange()).whereAny(headerMap).match();
    }


}
