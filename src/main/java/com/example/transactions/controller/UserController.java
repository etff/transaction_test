package com.example.transactions.controller;

import com.example.transactions.domain.User;
import com.example.transactions.domain.UserRepository;
import com.example.transactions.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserRepository userRepository;

    @GetMapping("/save")
    public User save(@RequestParam("name") String name, @RequestParam("age") Integer age) {
        return userService.save(name, age);
    }

    @RequestMapping("/{id}")
    public String get(@PathVariable Long id) {
        User user = userRepository.findById(id).get();
        System.out.println(user);

        return user.toString();
    }
}
