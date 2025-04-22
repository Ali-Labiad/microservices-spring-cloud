package com.ibm.bootcamp.spring.users.service;

import com.ibm.bootcamp.spring.users.exception.UserNotFountException;
import com.ibm.bootcamp.spring.users.model.User;
import com.ibm.bootcamp.spring.users.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Service
public class UsersApiService implements UsersApiServiceI {

    final private UserRepository userRepository;
    final private EventService eventService;
    final private BCryptPasswordEncoder bCryptPasswordEncoder;
    final private AuthenticationManager authenticationManager;
    final private JWTService jwtService;

    @Autowired
    public UsersApiService(UserRepository userRepository, EventService eventService, BCryptPasswordEncoder bCryptPasswordEncoder, AuthenticationManager authenticationManager, JWTService jwtService) {
        this.userRepository = userRepository;
        this.eventService = eventService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @Override
    public ResponseEntity<Void> addUser(User user) {

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        User u = userRepository.save(user);

        eventService.sendEvent(u);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(u.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @Override
    public ResponseEntity<Void> deleteUser(String userId) {

        ResponseEntity<User> u = findUserById(userId);
        userRepository.deleteById(userId);

        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<User> findUserById(String id) {
        Optional<User> optionalUser = userRepository.findById(id);

        if(optionalUser.isEmpty())
            throw new UserNotFountException("id:"+id);

        return ResponseEntity.ok(optionalUser.get());
    }

    @Override
    public ResponseEntity<List<User>> getUsers() {
        List<User> userList = userRepository.findAll();

        return ResponseEntity.ok(userList);
    }

    @Override
    public ResponseEntity<User> updateUser(String userId, User user) {
        ResponseEntity<User> u = findUserById(userId);
        user.setId(u.getBody().getId());
        User savedUser = userRepository.save(user);

        eventService.sendEvent(savedUser);

        return ResponseEntity.ok(savedUser);
    }

    public String verify(User user) {
        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getName(),user.getPassword()));

        if (authentication.isAuthenticated())
            return jwtService.generateToken(user.getName());

        return "fail";
    }
}
