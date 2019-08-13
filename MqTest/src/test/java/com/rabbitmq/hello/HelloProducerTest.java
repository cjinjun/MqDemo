package com.rabbitmq.hello;
import com.rabbitmq.MQApplication;
import com.rabbitmq.mq.hello.HelloProducer;
import com.rabbitmq.mq.publish.PublishProducer;
import com.rabbitmq.mq.route.RouteProducer;
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
    private RouteProducer routeProducer;
    @Test
    public void routeSend() throws Exception {
        routeProducer.send();
        Thread.sleep(10000);
    }

    @Autowired
    private TopicProducer topicProducer;
    @Test
    public void topicSend() throws Exception {
        topicProducer.sendTopic1();
        topicProducer.sendTopicN();
    }

    @Autowired
    private OrderService orderService;
    @Test
    public void saveServiceOrder() throws Exception {
        orderService.saveOrder();
    }

}