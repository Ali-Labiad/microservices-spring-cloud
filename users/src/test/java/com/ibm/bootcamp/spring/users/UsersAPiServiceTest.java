package com.ibm.bootcamp.spring.users;

import com.ibm.bootcamp.spring.users.model.User;
import com.ibm.bootcamp.spring.users.repository.UserRepository;
import com.ibm.bootcamp.spring.users.service.UsersApiService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class UsersAPiServiceTest {

    @InjectMocks
    UsersApiService usersApiService;

    @Mock
    UserRepository userRepository;

    @Test
    void findAnExistingUserById() {

        User user = User.builder().id("aaa").build();

        Mockito.when(userRepository.findById(Mockito.any())).thenReturn(Optional.of(user));

        ResponseEntity<User> responseEntity = usersApiService.findUserById(user.getId());

        assertThat(responseEntity.getBody().getId()).isEqualTo(user.getId());
    }

    @Test
    void findNotUserById() {

        User user = User.builder().id("aaa").build();

        Mockito.when(userRepository.findById(Mockito.any())).thenReturn(Optional.empty());

        ResponseEntity<User> responseEntity = usersApiService.findUserById(user.getId());

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }
}
