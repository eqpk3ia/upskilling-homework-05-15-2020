package com.sanket.todo.controller;

import java.util.Optional;

import com.sanket.todo.entity.Role;
import com.sanket.todo.repository.RoleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

    @PostMapping("/")
    public Role addRole(@RequestParam(required = true, name = "roleName") String roleName) {
        Role newRole = new Role();

        newRole.setRole(roleName.toUpperCase());

        return save(newRole);
    }

    @PatchMapping("/{id}")
    public Role updateUser(@PathVariable(name = "id", required = true) Long id,
            @RequestParam(name = "roleName", required = true) String roleName) {
        Role role = getById(id);

        if (null != role) {
            role.setRole(roleName.toUpperCase());
            save(role);
        }

        return role;
    }

}