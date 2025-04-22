package com.ibm.bootcamp.spring.users;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.bootcamp.spring.users.model.User;
import com.ibm.bootcamp.spring.users.repository.UserRepository;
import org.awaitility.Awaitility;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        properties = {
                "spring.kafka.consumer.value-deserializer=org.springframework.kafka.support.serializer.JsonDeserializer",
                "spring.kafka.consumer.properties[spring.json.trusted.packages]=com.ibm.bootcamp.spring.users.model"})
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class IntergrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    KafkaVerificator kafka;

    User user;

    @BeforeEach
    void setup() {

        user = User.builder().id("11").name("aaa")
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
    void saveUserTest() throws Exception {

        MvcResult mvcResult = mockMvc.perform(post("/api/v1/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isCreated())
                .andDo(print())
                .andReturn();

        String location = mvcResult.getResponse().getHeader("Location");
        assertThat(location).isNotEmpty();
        Awaitility.waitAtMost(10, TimeUnit.SECONDS).until(() -> kafka.received.contains(user));

    }

    @Test
    @Order(2)
    void getAllUsers() throws Exception {
        // precondition
        List<User> listUser = new ArrayList<>(Arrays.asList(user));
        userRepository.saveAll(listUser);

        mockMvc.perform(get("/api/v1/users"))

                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.size()", is(listUser.size())));
    }

    @Test
    @Order(3)
    void getFindById() throws Exception {
        // precondition
        User saved = userRepository.save(user);

        mockMvc.perform(get("/api/v1/users/{id}", user.getId()))

                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.name", is(saved.getName())))
                .andExpect(jsonPath("$.username", is(saved.getUsername())))
                .andExpect(jsonPath("$.email", is(saved.getEmail())));
    }

    @Test
    @Order(4)
    void deleteUserById() throws Exception {
        // precondition
        User saved = userRepository.save(user);

        mockMvc.perform(delete("/api/v1/users/{id}", user.getId()))
                .andExpect(status().isOk())
                .andDo(print());

        mockMvc.perform(get("/api/v1/users/{id}", user.getId()))
                .andExpect(status().isNotFound());
    }

    @Test
    @Order(5)
    void updateUser() throws Exception {

        User saved = userRepository.save(user);
        saved.setName("ali");


        mockMvc.perform(put("/api/v1/users/{id}", user.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(saved)))
                .andExpect(status().isOk())
                .andDo(print());

        mockMvc.perform(get("/api/v1/users/{id}", user.getId()))
                .andExpect(jsonPath("$.name", is(saved.getName())));
        Awaitility.waitAtMost(10, TimeUnit.SECONDS).until(() -> kafka.received.contains(user));

    }
}
