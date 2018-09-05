package com.spring.project.service;

import com.spring.project.pojo.User;

import java.util.List;

public interface UserService {
    User createOrUpdateUser(User user);

    User getUserById(Long userId);

    List<User> getAllUsers();

    void deleteUser(Long userId);
}
