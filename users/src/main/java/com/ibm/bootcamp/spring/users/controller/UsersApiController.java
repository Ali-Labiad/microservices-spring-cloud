package com.ibm.bootcamp.spring.users.controller;

import com.ibm.bootcamp.spring.users.api.UsersApi;
import com.ibm.bootcamp.spring.users.model.User;
import com.ibm.bootcamp.spring.users.service.UsersApiServiceI;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("/api/v1")
public class UsersApiController implements UsersApi {

    @Autowired
    UsersApiServiceI usersApiService;

    @Autowired
    MessageSource messageSource;

    @Override
    public ResponseEntity<Void> addUser(@Valid @RequestBody User user) {
        return usersApiService.addUser(user);
    }

    @Override
    public ResponseEntity<Void> deleteUser(String id) {
        return usersApiService.deleteUser(id);
    }

    @Override
    public ResponseEntity<User> findUserById(String id) {
        return usersApiService.findUserById(id);
    }


    @Override
    public ResponseEntity<List<User>> getUsers() {
        return usersApiService.getUsers();
    }

    @Override
    public ResponseEntity<User> updateUser(String userId, User user) {
        return usersApiService.updateUser(userId, user);
    }

    @GetMapping(path = "/hello")
    public String helloWorld() {
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage("good.morning.message",null,"Default Message",locale);
    }
}
