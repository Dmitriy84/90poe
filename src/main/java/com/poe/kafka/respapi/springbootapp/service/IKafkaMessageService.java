package com.poe.kafka.respapi.springbootapp.service;

import com.poe.kafka.respapi.springbootapp.dto.KafkaMessageModel;

import java.util.List;

public interface IKafkaMessageService {
    List<KafkaMessageModel> findAll();

    KafkaMessageModel save(KafkaMessageModel message) throws Exception;

    void consume(KafkaMessageModel message);

    List<KafkaMessageModel> getConsumedMessages();

    void cleanConsumedMessages();
}
