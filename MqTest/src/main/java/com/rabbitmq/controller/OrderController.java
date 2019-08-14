package com.rabbitmq.controller;

import com.rabbitmq.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Author Jason
 * @Date 2019/08/13
 * @Version 1.0
 */
@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping("saveOrder")
    public String saveOrder(){
        orderService.saveOrder();
        return "请求成功";
    }
}
