package com.sanket.todo.controller;

import com.sanket.todo.entity.Role;
import com.sanket.todo.repository.RoleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/roles")
public class RoleController extends AbstractController<Role> {

    @Autowired
    private RoleRepository roleRepository;

    public RoleController () {
        super();
    }

    @Override
    public JpaRepository<Role, Long> getRepository() {
        return roleRepository;
    }
   
}