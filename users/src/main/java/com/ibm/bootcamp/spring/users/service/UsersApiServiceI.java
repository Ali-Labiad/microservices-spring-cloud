package com.ibm.bootcamp.spring.users.service;

import com.ibm.bootcamp.spring.users.api.UsersApi;
import com.ibm.bootcamp.spring.users.controller.UsersApiController;
import com.ibm.bootcamp.spring.users.model.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * A delegate to be called by the {@link UsersApiController}}.
 * Implement this interface with a {@link org.springframework.stereotype.Service} annotated class.
 */
public interface UsersApiServiceI {

    /**
     * POST /users : Add a new user.
     * Add a new user.
     *
     * @param user Create a new user. (required)
     * @return The user has been created. (status code 201)
     * or Invalid request. (status code 400)
     * or Internal server error. (status code 500)
     * @see UsersApi#addUser
     */
    ResponseEntity<Void> addUser(User user);

    ;

    /**
     * DELETE /users/{id} : Delete user resource.
     * This can only be done by the logged in user.
     *
     * @param id Id value that need to be considered for getting a user. (required)
     * @return User deleted (status code 200)
     * or Invalid id supplied (status code 400)
     * or User not found (status code 404)
     * or Internal server error. (status code 500)
     * or Unexpected error (status code 200)
     * @see UsersApi#deleteUser
     */
    ResponseEntity<Void> deleteUser(String id);

    /**
     * GET /users/{id} : Finds User by userId.
     * Returns a single user.
     *
     * @param id Id value that need to be considered for getting a user. (required)
     * @return successful operation (status code 200)
     * or Invalid request. (status code 400)
     * or user not found. (status code 401)
     * or Internal server error. (status code 500)
     * or Unexpected error (status code 200)
     * @see UsersApi#findUserById
     */
    ResponseEntity<User> findUserById(String id);

    /**
     * GET /users : retrieve all users.
     * Return a list of users as an array.
     *
     * @return successful operation (status code 200)
     * or Internal server error. (status code 500)
     * @see UsersApi#getUsers
     */
    ResponseEntity<List<User>> getUsers();

    /**
     * PUT /users/{userId} : Update an existing user.
     * Update an existing user by Id.
     *
     * @param userId The id of user that need to be updated. (required)
     * @param user   Update an existent user in the store (required)
     * @return Successful operation (status code 200)
     * or Invalid request (status code 400)
     * or User not found (status code 404)
     * or Internal server error (status code 500)
     * or Unexpected error (status code 200)
     * @see UsersApi#updateUser
     */
    ResponseEntity<User> updateUser(String userId, User user);

}
