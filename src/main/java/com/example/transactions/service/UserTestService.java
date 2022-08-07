package com.example.transactions.service;

import com.example.transactions.domain.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserTestService {
    private final UserRepository userRepository;

    public UserTestService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void hello(Long id) {
        logging(userRepository.findById(id).get().toString());
    }

    private void logging(String str) {
        System.out.println("[hello] [ " + Thread.currentThread().getName() + "] " +str);
    }
}
