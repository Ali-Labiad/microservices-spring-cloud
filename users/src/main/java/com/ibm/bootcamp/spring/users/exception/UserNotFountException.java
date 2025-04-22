package com.ibm.bootcamp.spring.users.exception;

public class UserNotFountException extends RuntimeException {
    public UserNotFountException(String s) {
        super(s);
    }
}
