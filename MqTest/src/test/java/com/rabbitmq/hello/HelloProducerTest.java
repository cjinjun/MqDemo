package com.rabbitmq.hello;
import com.rabbitmq.MQApplication;
import com.rabbitmq.mq.header.HeaderProducer;
import com.rabbitmq.mq.hello.HelloProducer;
import com.rabbitmq.mq.fanout.PublishProducer;
import com.rabbitmq.mq.direct.DirectProducer;
import com.rabbitmq.mq.topic.TopicProducer;
import com.rabbitmq.mq.work.WorkProducer;
import com.rabbitmq.service.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MQApplication.class)
public class HelloProducerTest {

    @Autowired
    private HelloProducer helloProducer;
    @Test
    public void helloSend() throws Exception {
        helloProducer.send();
    }


    @Autowired
    private WorkProducer workProducer;
    @Test
    public void workSend() throws Exception {
        workProducer.send();
    }

    @Autowired
    private PublishProducer publishProducer;
    @Test
    public void publishSend() throws Exception {
        publishProducer.send();
        Thread.sleep(10000);
    }

    @Autowired
    private DirectProducer directProducer;
    @Test
    public void routeSend() throws Exception {
        directProducer.send();
        Thread.sleep(10000);
    }

    @Autowired
    private TopicProducer topicProducer;
    @Test
    public void topicSend() throws Exception {
        topicProducer.sendTopic1();
        topicProducer.sendTopicN();
        Thread.sleep(10000);
    }

    @Autowired
    private HeaderProducer headerProducer;
    @Test
    public void headerSend() throws Exception {
        headerProducer.send_match_no();
        headerProducer.send_match_all();
        headerProducer.send_match_any();
        Thread.sleep(10000);
    }


    @Autowired
    private OrderService orderService;
    @Test
    public void saveServiceOrder() throws Exception {
        orderService.saveOrder();
    }

}