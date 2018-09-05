package com.spring.project.service;

import com.google.common.collect.Lists;
import com.spring.project.pojo.User;
import com.spring.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

@Lazy
@Service
public class UserServiceImpl implements UserService{

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User createOrUpdateUser(User user) {
        User mongoUser = userRepository.findByEmailOrUsername(user.getEmail(), user.getUsername());
        if (mongoUser != null) {
            user.setUserId(mongoUser.getUserId());
        } else {
            user.setUserId(generateUserId(user));
        }
        user.setPassword(encodePassword(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public User getUserById(Long userId) {
        return userRepository.findByUserId(userId);
    }

    @Override
    public List<User> getAllUsers() {
        return newArrayList(userRepository.findAll());
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    private String encodePassword(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }

    private Long generateUserId(User user) {
        String concatenated = user.getEmail() + user.getUsername();
        return (long) concatenated.hashCode();
    }
}
