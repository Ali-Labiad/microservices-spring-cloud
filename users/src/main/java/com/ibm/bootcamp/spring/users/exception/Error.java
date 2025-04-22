package com.ibm.bootcamp.spring.users.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * Error
 */

@Data
@Builder
@AllArgsConstructor
public class Error {

    private String code;
    private String message;

}

