package com.example.transactions.service;

import com.example.transactions.domain.User;
import com.example.transactions.domain.UserCreatedEvent;
import com.example.transactions.domain.UserRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Transactional
@Service
public class UserService {
    private final UserRepository userRepository;
    private final ApplicationEventPublisher eventPublisher;
    private final UserTestService userTestService;


    public UserService(UserRepository userRepository, ApplicationEventPublisher eventPublisher, UserTestService userTestService) {
        this.userRepository = userRepository;
        this.eventPublisher = eventPublisher;
        this.userTestService = userTestService;
    }

    public User save(String name, int age) {
        System.out.println("Begin user create 1");
        User user = new User(1L, name, age);
        System.out.println("Begin user create 2");
        user = userRepository.save(user);
        eventPublisher.publishEvent(new UserCreatedEvent(user));
        System.out.println("End user create 3");

        System.out.println("End user create 4");

        userTestService.hello(1L);


        return user;
    }

    private void logging(String str) {
        System.out.println("[save] [" + Thread.currentThread().getName() + "]" + str);
    }
}
