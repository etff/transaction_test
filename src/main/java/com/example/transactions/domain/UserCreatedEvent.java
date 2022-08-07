package com.example.transactions.domain;

import org.springframework.context.ApplicationEvent;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;

public class UserCreatedEvent extends ApplicationEvent {
    private User user;
    public UserCreatedEvent(Object source) {
        super(source);
    }

    public UserCreatedEvent(Object source, User user) {
        super(source);
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
