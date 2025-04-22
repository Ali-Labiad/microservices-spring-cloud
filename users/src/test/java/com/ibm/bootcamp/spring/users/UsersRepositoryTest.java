package com.ibm.bootcamp.spring.users;

import com.ibm.bootcamp.spring.users.model.User;
import com.ibm.bootcamp.spring.users.repository.UserRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UsersRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    private String userId;

    @Autowired
    private MongoTemplate mongoTemplate;

    User user;

    @BeforeEach
    void setup() {
        user = User.builder().name("aaa")
                .email("aaa@gmail.com")
                .username("bbb")
                .build();
        mongoTemplate.dropCollection(User.class);
    }

    @BeforeEach
    void tearDown() {
        mongoTemplate.dropCollection(User.class);
    }

    @Test
    @Order(1)
    @DisplayName("Test 1: Save user Test")
    void saveUserTest() {

        userRepository.save(user);

        //Check
        System.out.println("id....:" + user.getId());
        assertThat(user.getId()).isNotBlank();

    }

    @Test
    @Order(2)
    @DisplayName("Test 2 : Get user by id")
    void getUserBy() {

        User saved = userRepository.save(user);

        Optional<User> optionalUser = userRepository.findById(saved.getId());
        assertThat(optionalUser.get().getId()).isNotNull();
    }

    @Test
    @Order(2)
    @DisplayName("Test 2 : Get all users")
    void getAllUsers() {

        userRepository.saveAll(Arrays.asList(user, user));

        List<User> userList = userRepository.findAll();
        assertThat(userList.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("Test 3 : delete user by id")
    @Order(3)
    void deleteUserBy() {

        User saved = userRepository.save(user);

        userRepository.deleteById(saved.getId());
        Optional<User> userDeleted = userRepository.findById(saved.getId());
        assertThat(userDeleted.isPresent()).isFalse();
    }
}
