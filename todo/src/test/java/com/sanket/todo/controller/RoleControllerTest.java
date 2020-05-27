package com.sanket.todo.controller;

import com.sanket.todo.entity.Role;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

public class RoleControllerTest extends AbstractControllerTest<Role> {

    private static String URL = "/roles/";

    @Override
    public String getEndPointUrl() {
        return URL;
    }

    @Override
    public MultiValueMap<String, String> getAddRecordDetails() {
        MultiValueMap<String, String> addRole = new LinkedMultiValueMap<>();

        addRole.add("roleName", "newRole");

        return addRole;
    }
    
}