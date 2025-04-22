package com.ibm.bootcamp.spring.users.service;

import com.ibm.bootcamp.spring.users.model.User;

public interface EventService {
    void sendEvent(final User event);
}
