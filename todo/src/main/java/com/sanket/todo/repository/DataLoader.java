package com.sanket.todo.repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import com.sanket.todo.entity.Role;
import com.sanket.todo.entity.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataLoader {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    public DataLoader(UserRepository userRepository, RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;

        LoadInitData();
    }

    private void LoadInitData() {
        List<Role> roles = new ArrayList<Role>();

        Role admin = new Role("ADMIN");
        Role user = new Role("USER");
        Role testRole = new Role("TESTROLE");
        
        roles.add(admin);
        roles.add(user);
        roles.add(testRole);

        roleRepository.saveAll(roles);

         List<User> users = new ArrayList<User>();

        users.add(new User("admin@Test.com", passwordEncoder.encode("pwd"), "Admin", "Test", 1,
                new HashSet<Role>(Arrays.asList(admin))));

        users.add(new User("user@Test.com", passwordEncoder.encode("pwd"), "User", "Test", 1,
                new HashSet<Role>(Arrays.asList(user))));
        userRepository.saveAll(users); 
    }
}