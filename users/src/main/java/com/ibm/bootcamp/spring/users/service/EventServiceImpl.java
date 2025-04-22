package com.ibm.bootcamp.spring.users.service;

import com.ibm.bootcamp.spring.users.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class EventServiceImpl implements EventService {
    private final KafkaTemplate<String, User> kafkaTemplate;

    @Autowired
    public EventServiceImpl(KafkaTemplate<String, User> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }


    @Override
    public void sendEvent(User event) {
        this.kafkaTemplate.send("users-v1", event);
    }
}
