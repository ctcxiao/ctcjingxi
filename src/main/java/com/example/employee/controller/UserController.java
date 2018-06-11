package com.example.employee.controller;

import com.example.employee.entity.Users;
import com.example.employee.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/user/{id}", method = RequestMethod.POST)
    public void createUser(@PathVariable("id") int id){
        Users users = new Users(id, "ctc");
        userRepository.save(users);
    }
}
