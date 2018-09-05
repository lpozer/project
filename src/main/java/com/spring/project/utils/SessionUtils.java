package com.spring.project.utils;

import com.spring.project.pojo.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SessionUtils {

    public static User getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (User) authentication.getPrincipal();
    }
}
