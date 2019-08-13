package com.rabbitmq.mq.direct;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @Description: direct 模式
 * @Author Jason
 * @Date 2019/08/13
 * @Version 1.0
 */
@Component
@Slf4j
public class DirectProducer {

    @Autowired
    private AmqpTemplate amqpTemplate;

    String[] routeKeys = {ROUTE_KEY, ROUTE_KEY_2};

    public void send() {
        for (int i = 0; i < 10; i++) {
            String message = "Direct mq " + i;
            //第一个参数为exchangeName,第二个是router keyname，第三个是发送内容
            amqpTemplate.convertAndSend(EXCHANGE_NAME, routeKeys[0], message);
        }
        log.warn("Direct-Model send success");
    }


    private static final String QUEUE_NAME = "mq.queue.direct.one";
    private static final String QUEUE_NAME_2 = "mq.queue.direct.two";
    private static final String EXCHANGE_NAME = "mq.exchange.direct";
    private static final String ROUTE_KEY = "mq.direct.booking";
    private static final String ROUTE_KEY_2 = "mq.direct.create";

    /**
     * 创建QUEUE
     *
     * @return
     */
    @Bean
    public Queue routeQueueOne() {
        return new Queue(QUEUE_NAME, true);
    }

    @Bean
    public Queue routeQueueTwo() {
        return new Queue(QUEUE_NAME_2, true);
    }

    /**
     * 创建 rirect exchange
     *
     * @return
     */
    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(EXCHANGE_NAME);
    }

    /**
     * queue 与 交换机绑定  指定路由KEY1
     *
     * @return
     */
    @Bean
    public Binding bindingRouteExchangeWithQueue() {
        return BindingBuilder.bind(routeQueueOne()).to(directExchange()).with(ROUTE_KEY);
    }

    /**
     * queue2 与 交换机绑定 指定路由KEY2
     *
     * @return
     */
    @Bean
    public Binding bindingRouteExchangeWithQueue2() {
        return BindingBuilder.bind(routeQueueTwo()).to(directExchange()).with(ROUTE_KEY_2);
    }

}
