package com.rabbitmq.service;

import com.alibaba.fastjson.JSONObject;

import com.rabbitmq.mapper.OrderMapper;
import com.rabbitmq.model.OrderInfoModel;
import com.rabbitmq.mqconfig.OrderMQMsgConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@Slf4j
public class OrderService implements RabbitTemplate.ConfirmCallback, RabbitTemplate.ReturnCallback {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private OrderMapper orderMapper;


    /**
     * 创建订单
     */
    public void saveOrder() {
        OrderInfoModel orderInfoModel = new OrderInfoModel();
        orderInfoModel.initOrder();
        orderInfoModel.setAmount(new BigDecimal(100));
        orderInfoModel.setCustomerName("张三");
        Long result = orderMapper.insert(orderInfoModel);
        //send mq 消费积分
        if (result == 1L) {
            sendMsg(orderInfoModel);
        }
    }

    /**
     * 发送消息
     *
     * @param orderInfoModel
     */
    public void sendMsg(OrderInfoModel orderInfoModel) {
        rabbitTemplate.setMandatory(true);
        rabbitTemplate.setConfirmCallback(this);

        //消息封装
        Message message = MessageBuilder.withBody(JSONObject.toJSONString(orderInfoModel).getBytes()).setContentType(MessageProperties.CONTENT_TYPE_JSON)
                .setContentEncoding("utf-8").setMessageId(orderInfoModel.getOrderNo()).build();

        CorrelationData correlationData = new CorrelationData(orderInfoModel.getOrderNo());
        rabbitTemplate.convertAndSend(OrderMQMsgConfig.EXCHANGE_NAME_TRANSACTION,
                OrderMQMsgConfig.ROUTE_NAME_TRANSACTION, message, correlationData);
        log.info("Transactional Message success.");
    }

    /**
     * 用来确认消息是否有送达消息队列
     *
     * @param correlationData
     * @param ack
     * @param cause
     */
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        log.info(JSONObject.toJSONString(correlationData));
        if (ack) {
            log.info("确认消息已送达-success");
        } else {
            // retry
            log.info("消息未送达-failed");
        }
    }

    /**
     * 消息找不到对应的Exchange会先触发此方法
     *
     * @param message
     * @param replyCode
     * @param replyText
     * @param tempExchange
     * @param tmpRoutingKey
     */
    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String tempExchange
            , String tmpRoutingKey) {
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Send message failed:" + replyCode + " " + replyText);
        rabbitTemplate.send(message);
    }
}
