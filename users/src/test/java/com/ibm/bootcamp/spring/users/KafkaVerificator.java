package com.ibm.bootcamp.spring.users;

import com.ibm.bootcamp.spring.users.model.User;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class KafkaVerificator {

    final List<User> received = new ArrayList<>();

    @KafkaListener(topics = "${spring.kafka.template.default-topic}", groupId = "unit")
    public void receive(ConsumerRecord<String, User> consumerRecord) {
        received.add(consumerRecord.value());
    }
}
