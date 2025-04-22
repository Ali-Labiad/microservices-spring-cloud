package com.ibm.bootcamp.spring.datalake.kafka;

import com.ibm.bootcamp.spring.datalake.db.UserRepository;
import com.ibm.bootcamp.spring.datalake.mapping.UserMapper;
import com.ibm.bootcamp.spring.datalake.model.UserDTO.User;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class Consumer {

    private final UserMapper userMapper;
    private final UserRepository userRepository;

    public Consumer(UserMapper userMapper, UserRepository userRepository) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
    }

    @Bean
    public java.util.function.Consumer<User> userConsumer() {
        return user -> {
            System.out.println("Processing " + user);
            userRepository.save(userMapper.fromDto(user));
        };
    }
}
