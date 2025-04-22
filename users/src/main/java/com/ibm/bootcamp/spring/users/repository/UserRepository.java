package com.ibm.bootcamp.spring.users.repository;

import com.ibm.bootcamp.spring.users.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    List<User> findByUsername(String username);
}
