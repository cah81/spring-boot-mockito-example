package com.knf.dev.mockito.controller;

import com.knf.dev.mockito.entity.User;
import com.knf.dev.mockito.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserRestController {
    @Autowired
    private UserService userService;

    @GetMapping("/user/{username}")
    public User getUserbyname(@PathVariable String username){
        return userService.getUserByName(username);
    }
    @PostMapping("/user")
    public User saveUser(@RequestBody User user){
        return userService.saveUser(user);
    }

    @GetMapping("/user")
    public List<User> getallUsers(){
        return userService.getAllUsers();
    }
}
