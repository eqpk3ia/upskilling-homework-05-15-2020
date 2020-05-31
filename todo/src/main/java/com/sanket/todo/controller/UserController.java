package com.sanket.todo.controller;

import java.util.Arrays;
import java.util.HashSet;

import com.sanket.todo.entity.Role;
import com.sanket.todo.entity.User;
import com.sanket.todo.repository.RoleRepository;
import com.sanket.todo.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController extends AbstractController<User> {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public JpaRepository<User, Long> getRepository() {
        return userRepository;
    }

    @PutMapping("/")
    public User addUser(@RequestParam(required = true, name = "firstName") String firstName,
        @RequestParam(required = true, name = "lastName") String lastName,
        @RequestParam(required = true, name = "email") String email,
        @RequestParam(required = true, name = "password") String password) {

        Role adminRole = roleRepository.findByRole("ADMIN");

        User newUser = new User(email, passwordEncoder.encode(password), firstName, lastName, 1,
                new HashSet<Role>(Arrays.asList(adminRole)));

        return save(newUser);
    }

    @PostMapping("/{id}")
    public User updateUser(@PathVariable(name = "id", required = true) Long id,
        @RequestParam(required = false, name = "firstName") String firstName,
        @RequestParam(required = false, name = "lastName") String lastName,
        @RequestParam(required = false, name = "email") String email,
        @RequestParam(required = false, name = "password") String password) {
        
        User user = getById(id);
        if (null != user) {
            if (null != firstName && !firstName.trim().isEmpty()) {
                user.setFirstName(firstName);
            }

            if (null != lastName && !lastName.trim().isEmpty()) {
                user.setLastName(lastName);
            }

            if (null != email && !firstName.trim().isEmpty()) {
                user.setEmail(email);
            }

            if (null != password && !password.trim().isEmpty()) {
                user.setPassword(passwordEncoder.encode(password));
            }

            save(user);
        }

        return user;
    }

}