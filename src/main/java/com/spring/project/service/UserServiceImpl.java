package com.spring.project.service;

import com.spring.project.pojo.User;
import com.spring.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Lazy
@Service
public class UserServiceImpl implements UserService{

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(User user) {
    	user.setUserId(userRepository.count() + 1);
    	user.setPassword(encodePassword(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public User getUserById(Long userId) {
        return userRepository.findByUserId(userId);
    }

    private String encodePassword(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }
}
