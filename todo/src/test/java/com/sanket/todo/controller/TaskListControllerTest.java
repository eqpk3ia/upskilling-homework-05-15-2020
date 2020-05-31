package com.sanket.todo.controller;

import com.sanket.todo.entity.Role;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

public class TaskListControllerTest extends AbstractControllerTest<Role> {

    private static String URL = "/lists/";

    @Override
    protected String getEndPointUrl() {
        return URL;
    }

    @Override
    protected MultiValueMap<String, String> getAddRecordDetails() {
        MultiValueMap<String, String> addRole = new LinkedMultiValueMap<>();

        addRole.add("taskListName", "PMO-1234");

        return addRole;
    }

    @Override
    protected MultiValueMap<String, String> getUpdateRecordDetails() {
        MultiValueMap<String, String> updateRole = new LinkedMultiValueMap<>();

        updateRole.add("taskListName", "UPDATE_PMO-12345" + getIdToUpdate());

        return updateRole;
    }

    @Override
    protected Long getIdToFindById() {
        return 7L;
    }

    @Override
    protected Long getIdToUpdate() {
        return 7L;
    }

    @Override
    protected Long getIdToDelete() {
        return null;
    }

    @Override
    protected boolean executeDeleteAll() {
        return Boolean.FALSE;
    }
}