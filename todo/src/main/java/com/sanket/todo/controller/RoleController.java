package com.sanket.todo.controller;

import javax.validation.constraints.NotBlank;

import com.sanket.todo.entity.Role;
import com.sanket.todo.repository.RoleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/roles")
public class RoleController extends AbstractController<Role> {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public JpaRepository<Role, Long> getRepository() {
        return roleRepository;
    }

    @PutMapping("/")
    public Role addRole(@RequestParam(required = true, name = "name")  @NotBlank String name) {
        Role newRole = new Role();

        newRole.setRole(name.toUpperCase());

        return save(newRole);
    }

    @PostMapping("/")
    public Role updateUser(@RequestParam(name = "id", required = true) Long id,
            @RequestParam(name = "name", required = true) @NotBlank String name) {
        Role role = getById(id);

        if (null != role) {
            role.setRole(name.toUpperCase());
            save(role);
        }

        return role;
    }

}