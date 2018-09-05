package com.spring.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.spring.project.pojo.User;
import com.spring.project.service.UserService;

import java.util.List;

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/user", method = RequestMethod.PUT)
	public User createUser(@RequestBody User user) {
		return userService.createOrUpdateUser(user);
	}

	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
	public User getUser(@PathVariable Long id){
		return userService.getUserById(id);
	}

	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public List<User> getUsers() {
		return userService.getAllUsers();
	}

	@RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
	public String deleteUser(@PathVariable Long id) {
		userService.deleteUser(id);
		return String.format("User %s was deleted.", id);
	}
}
