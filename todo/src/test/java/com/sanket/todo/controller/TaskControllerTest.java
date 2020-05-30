package com.sanket.todo.controller;

import com.sanket.todo.entity.Task;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

public class TaskControllerTest extends AbstractControllerTest<Task> {

    private static String URL = "/tasks/";

    @Override
    protected String getEndPointUrl() {
        return URL;
    }

    @Override
    protected MultiValueMap<String, String> getAddRecordDetails() {
        MultiValueMap<String, String> addUser = new LinkedMultiValueMap<>();

        addUser.add("name", "First Task");
        addUser.add("descr", "First Task Description");

        return addUser;
    }

    @Override
    protected MultiValueMap<String, String> getUpdateRecordDetails() {
        MultiValueMap<String, String> updateUser = new LinkedMultiValueMap<>();

        updateUser.add("name", "First Update Task");
        updateUser.add("descr", "First Updated Description");

        return updateUser;
    }

    @Override
    protected Long getIdToFindById() {
        return 8L;
    }

    @Override
    protected Long getIdToUpdate() {
        return 8L;
    }

    @Override
    protected Long getIdToDelete() {
        return 8L;
    }

    @Override
    protected boolean executeDeleteAll() {
        return Boolean.FALSE;
    }
}