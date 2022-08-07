package com.example.transactions.listener;

import com.example.transactions.domain.User;
import com.example.transactions.domain.UserCreatedEvent;
import com.example.transactions.domain.UserRepository;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionalEventListener;

import javax.persistence.EntityManager;

@Component
public class UserEventListener {
    private final UserRepository userRepository;

    public UserEventListener(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @EventListener(UserCreatedEvent.class)
    public void onUserCreateEvent(UserCreatedEvent event) {
        logging("EventListener", "Begin onUserCreatedEvent 5");
        logging("EventListener", event.toString());
        logging("EventListener", "End onUserCreatedEvent 6");
    }

    @TransactionalEventListener(UserCreatedEvent.class)
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Async
    public void onTUserCreateEvent(UserCreatedEvent event) {
        User user = userRepository.findById(event.getUser().getId()).get();

        user.setName("foo2");

        logging("TransactionalEventListener", "Begin onUserCreatedEvent 7");
        logging("TransactionalEventListener", event.toString());
        logging("TransactionalEventListener", "End onUserCreatedEvent 8");
    }

    private void logging(String prefix, String str) {
        System.out.println("[" + prefix + "] [" + Thread.currentThread().getName() + "] " + str);
    }
}
