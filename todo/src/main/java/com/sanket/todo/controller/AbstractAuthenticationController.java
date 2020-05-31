package com.sanket.todo.controller;

import com.sanket.todo.entity.TodoEntity;
import com.sanket.todo.entity.User;
import com.sanket.todo.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public abstract class AbstractAuthenticationController<E extends TodoEntity> extends AbstractController<E> {

    @Autowired
    private UserRepository userRepository;

    protected User getCurrentUser() {
        User currentUser = null;

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUserName = authentication.getName();

            if (null != currentUserName && !currentUserName.trim().isEmpty()) {
                currentUser = userRepository.findByEmail(currentUserName);
            }
        }

        return currentUser;
    }
}