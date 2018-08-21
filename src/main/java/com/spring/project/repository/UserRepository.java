package com.spring.project.repository;

import com.spring.project.pojo.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import java.lang.String;
import java.util.List;


public interface UserRepository extends PagingAndSortingRepository<User, Long> {
	List<User> findByFirstName(String firstname);

	User findByUserId(Long userId);

	User findByUsername(String username);
}

