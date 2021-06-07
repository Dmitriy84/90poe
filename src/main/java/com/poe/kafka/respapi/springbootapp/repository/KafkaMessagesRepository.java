package com.poe.kafka.respapi.springbootapp.repository;

import com.poe.kafka.respapi.springbootapp.dto.KafkaMessageModel;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;

@Repository
public interface KafkaMessagesRepository extends CrudRepository<KafkaMessageModel, Long> {

}