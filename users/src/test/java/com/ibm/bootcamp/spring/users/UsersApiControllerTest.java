package com.ibm.bootcamp.spring.users;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.bootcamp.spring.users.controller.UsersApiController;
import com.ibm.bootcamp.spring.users.model.User;
import com.ibm.bootcamp.spring.users.service.UsersApiServiceI;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@WebMvcTest(UsersApiController.class)
class UsersApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    UsersApiServiceI usersApiServiceMock;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void findUserByIdTestUserExist() throws Exception {
        String URI = "/api/v1/users/1";
        User user = User.builder().id("111").name("aaa").build();

        Mockito.when(usersApiServiceMock.findUserById(Mockito.any())).thenReturn(ResponseEntity.ok(user));

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URI)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        int statusCode = mvcResult.getResponse().getStatus();
        String expected = "{\"id\": \"111\"}";
        String contentAsString = mvcResult.getResponse().getContentAsString();

        assertThat(statusCode).isEqualTo(200);
        JSONAssert.assertEquals(expected, contentAsString, JSONCompareMode.LENIENT);
    }

    @Test
    void findUserByIdTestUserNotFound() throws Exception {
        String URI = "/api/v1/users/2";
        Mockito.when(usersApiServiceMock.findUserById(Mockito.any())).thenReturn(ResponseEntity.notFound().build());

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URI)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        int statusCode = mvcResult.getResponse().getStatus();
        assertThat(statusCode).isEqualTo(404);
    }

    @Test
    void findUserByIdTestUserNoUrlMatch() throws Exception {
        String URI = "/api/v1/users/";
        Mockito.when(usersApiServiceMock.findUserById(Mockito.any())).thenReturn(ResponseEntity.notFound().build());

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URI)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        int statusCode = mvcResult.getResponse().getStatus();
        assertThat(statusCode).isEqualTo(404);

    }

    @Test
    void findUserByIdTestUserIdFormatIncorrect() throws Exception {
        String URI = "/api/v1/users/sss";
        Mockito.when(usersApiServiceMock.findUserById(Mockito.any())).thenReturn(ResponseEntity.badRequest().build());

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URI)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        int statusCode = mvcResult.getResponse().getStatus();
        assertThat(statusCode).isEqualTo(400);

    }

    @Test
    void getListOfUsers() throws Exception {
        String URI = "/api/v1/users";
        User user1 = User.builder().name("aaa").build();
        User user2 = User.builder().name("bbb").build();
        ArrayList<User> list = new ArrayList<>(Arrays.asList(user1, user2));

        Mockito.when(usersApiServiceMock.getUsers()).thenReturn(ResponseEntity.ok(list));

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URI)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        int status = mvcResult.getResponse().getStatus();
        String actualResult = mvcResult.getResponse().getContentAsString();

        assertThat(status).isEqualTo(200);
        JSONAssert.assertEquals("[{\"name\":\"aaa\"}, {\"name\":\"bbb\"}]", actualResult, JSONCompareMode.LENIENT);

    }

    @Test
    void addUserSuccess201Test() throws Exception {
        String URI = "/api/v1/users";
        User user1 = User.builder().id("111").name("aaa").email("aaa@gmail.com").build();
        Mockito.when(usersApiServiceMock.addUser(Mockito.any())).thenReturn(ResponseEntity.created(new URI("/api/v1/users/1")).build());

        RequestBuilder request = MockMvcRequestBuilders.post(URI).
                accept(MediaType.APPLICATION_JSON).
                contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user1));

        MvcResult mvcResult = mockMvc.perform(request).andReturn();
        String uriLocation = mvcResult.getResponse().getHeader("LOCATION");

        int status = mvcResult.getResponse().getStatus();
        assertThat(status).isEqualTo(201);

        assertThat(uriLocation).isEqualTo("/api/v1/users/1");

    }

    @Test
    void addUserNotValidRequestCode400Test() throws Exception {
        String URI = "/api/v1/users";
        User user1 = User.builder().name(null).email("aaa@gmail.com").build();
        Mockito.when(usersApiServiceMock.addUser(Mockito.any())).thenReturn(ResponseEntity.created(new URI("/api/v1/users/1")).build());

        RequestBuilder request = MockMvcRequestBuilders.post(URI).
                accept(MediaType.APPLICATION_JSON).
                contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user1));

        MvcResult mvcResult = mockMvc.perform(request).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertThat(status).isEqualTo(400);

    }
}
