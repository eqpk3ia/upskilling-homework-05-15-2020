package com.sanket.todo.controller;

import com.sanket.todo.entity.User;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

public class UserControllerTest extends AbstractControllerTest<User> {

    private static String URL = "/users/";

    @Override
    protected String getEndPointUrl() {
        return URL;
    }

    @Override
    protected MultiValueMap<String, String> getAddRecordDetails() {
        MultiValueMap<String, String> addUser = new LinkedMultiValueMap<>();

        addUser.add("firstName", "FirstTest");
        addUser.add("lastName", "LastTest");
        addUser.add("email", "First.Last@email.com");
        addUser.add("password", "password1");

        return addUser;
    }

    @Override
    protected MultiValueMap<String, String> getUpdateRecordDetails() {
        MultiValueMap<String, String> updateUser = new LinkedMultiValueMap<>();

        updateUser.add("firstName", "FirstUpdate");
        updateUser.add("lastName", "LastUpdate");

        return updateUser;
    }

    @Override
    protected Long getIdToFindById() {
        return 4L;
    }

    @Override
    protected Long getIdToUpdate() {
        return 6L;
    }

    @Override
    protected Long getIdToDelete() {
        return 6L;
    }

    @Override
    protected boolean executeDeleteAll() {
        return Boolean.FALSE;
    }
}