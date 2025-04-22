package com.ibm.bootcamp.spring.datalake.db;

import com.ibm.bootcamp.spring.datalake.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

}
