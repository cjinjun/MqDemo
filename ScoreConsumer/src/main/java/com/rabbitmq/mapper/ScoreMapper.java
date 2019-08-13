package com.rabbitmq.mapper;


import com.rabbitmq.model.ScoreModel;

public interface ScoreMapper {

    Long insert(ScoreModel scoreModel);

    ScoreModel getByOrderNo(String orderNo);
}
