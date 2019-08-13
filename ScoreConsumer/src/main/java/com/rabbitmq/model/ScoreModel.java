package com.rabbitmq.model;

import lombok.Data;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

@Data
public class ScoreModel {

    private Long id;
    private String orderNo;
    private Integer score;

    @Override
    public String toString(){
        return ReflectionToStringBuilder.toString(this);
    }

}
