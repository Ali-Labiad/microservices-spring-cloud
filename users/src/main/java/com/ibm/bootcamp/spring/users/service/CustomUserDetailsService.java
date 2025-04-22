package com.ibm.bootcamp.spring.users.service;

import com.ibm.bootcamp.spring.users.model.User;
import com.ibm.bootcamp.spring.users.model.UserPrincipal;
import com.ibm.bootcamp.spring.users.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        List<User> users = userRepository.findByUsername(username);

        if (users.isEmpty()){
            throw new UsernameNotFoundException("user not fount");
        }
        User u = users.get(0);
        return new UserPrincipal(u);
    }
}
