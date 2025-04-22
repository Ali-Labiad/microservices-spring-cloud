package com.ibm.bootcamp.spring.users.security;

import com.ibm.bootcamp.spring.users.model.User;
import com.ibm.bootcamp.spring.users.service.CustomUserDetailsService;
import com.ibm.bootcamp.spring.users.service.UsersApiService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;

@RestController
public class Login {

    @Autowired
    UsersApiService service;

    @PostMapping("/login")
    public String login(@RequestBody User user){

        return service.verify(user);
    }
}
