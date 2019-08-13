package com.rabbitmq.model;

import lombok.Data;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

/**
 * @Description: 订单
 * @Author JASON
 * @Date 2019/08/13
 * @Version 1.0
 */
@Data
public class OrderInfoModel implements Serializable {
    private static final long serialVersionUID = -7509457735448350575L;

    private Long id;
    private String orderNo;
    private BigDecimal amount;
    private String customerName;

    public void initOrder(){
        this.setOrderNo(UUID.randomUUID().toString());
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
