package com.sanket.todo.controller;

import com.sanket.todo.entity.Role;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

public class RoleControllerTest extends AbstractControllerTest<Role> {

    private static String URL = "/roles/";

    @Override
    protected String getEndPointUrl() {
        return URL;
    }

    @Override
    protected MultiValueMap<String, String> getAddRecordDetails() {
        MultiValueMap<String, String> addRole = new LinkedMultiValueMap<>();

        addRole.add("roleName", "newRole");

        return addRole;
    }

    @Override
    protected MultiValueMap<String, String> getUpdateRecordDetails() {
        MultiValueMap<String, String> updateRole = new LinkedMultiValueMap<>();

        updateRole.add("roleName", "updatedRole" + getIdToUpdate());

        return updateRole;
    }

    @Override
    protected Long getIdToFindById() {
        return 2L;
    }

    @Override
    protected Long getIdToUpdate() {
        return 3L;
    }

    @Override
    protected Long getIdToDelete() {
        return 3L;
    }

    @Override
    protected boolean executeDeleteAll() {
        return Boolean.FALSE;
    }
}