package com.poe.kafka.respapi.springbootapp.service;

import com.poe.kafka.respapi.springbootapp.dto.KafkaMessageModel;
import com.poe.kafka.respapi.springbootapp.repository.KafkaMessagesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@PropertySource("classpath:application.properties")
public class KafkaMessageService implements IKafkaMessageService {
    @Autowired
    private KafkaMessagesRepository repository;

    private List<KafkaMessageModel> consumed = new ArrayList<>();

    @Override
    public List<KafkaMessageModel> findAll() {
        return (List<KafkaMessageModel>) repository.findAll();
    }

    @Override
    public KafkaMessageModel save(KafkaMessageModel message) throws Exception {
        if (repository.findById(message.getId()).isEmpty())
            return repository.save(message);

        throw new Exception("message with '" + message.getId() + "' already present");
    }

    @Override
    @KafkaListener(topics = "${spring.kafka.topic}", groupId = "${spring.kafka.consumer.groupId}")
    public void consume(KafkaMessageModel message) {
        System.out.println("Message consumed: " + message);
        consumed.add(message);
    }

    @Override
    public List<KafkaMessageModel> getConsumedMessages() {
        return consumed;
    }

    @Override
    public void cleanConsumedMessages() {
        consumed.clear();
    }
}
