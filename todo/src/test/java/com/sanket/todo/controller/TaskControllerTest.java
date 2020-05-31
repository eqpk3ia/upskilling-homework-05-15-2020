package com.sanket.todo.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.sanket.todo.entity.Task;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
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
        // addUser.add("descr", "First Task Description");

        return addUser;
    }

    @Override
    protected MultiValueMap<String, String> getUpdateRecordDetails() {
        MultiValueMap<String, String> updateUser = new LinkedMultiValueMap<>();

        updateUser.add("name", "First Update Task");
        updateUser.add("descr", "First Updated Description");

        return updateUser;
    }

    @Test
    public void addNewTaskWithTaskListId() throws Exception {

        System.out.println("**************** addNewTaskWithTaskListId Start ****************");

        MultiValueMap<String, String> updateEntity = getUpdateRecordDetails();
        /*this.mockMvc
                .perform(put(getEndPointUrl() + "lists/" + "9")
                        .header(HttpHeaders.AUTHORIZATION, getBasicUserDigestHeaderValue()).params(updateEntity))
                .andDo(print()).andExpect(status().isOk());
*/
        System.out.println("**************** addNewTaskWithTaskListId End ****************");
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