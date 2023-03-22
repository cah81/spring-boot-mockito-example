package com.knf.dev.mockito.service;

import com.knf.dev.mockito.entity.User;

import java.util.List;

public interface UserService {
    public User getUserByName(String name);
    public User saveUser(User user);

    public List<User> getAllUsers();

}
