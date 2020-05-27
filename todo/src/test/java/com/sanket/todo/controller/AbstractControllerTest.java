package com.sanket.todo.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Base64;
import java.util.Map;

import com.sanket.todo.entity.TodoEntity;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.MultiValueMap;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public abstract class AbstractControllerTest<E extends TodoEntity> {

    private static String BASIC_ADMIN_DIGEST_HEADER_VALUE = "Basic "
            + new String(Base64.getEncoder().encode(("admin@Test.com:pwd").getBytes()));

    private static String BASIC_USER_DIGEST_HEADER_VALUE = "Basic "
            + new String(Base64.getEncoder().encode(("user@Test.com:pwd").getBytes()));

    @LocalServerPort
    protected int port;
    @Autowired
    protected MockMvc mockMvc;

    public abstract String getEndPointUrl();

    public abstract MultiValueMap<String, String> getAddRecordDetails();

    @Test
    public void getAll() throws Exception {

        System.out.println("**************** Get All Start ****************");

        this.mockMvc.perform(get(getEndPointUrl()).header(HttpHeaders.AUTHORIZATION, getBasicAdminDigestHeaderValue()))
                .andDo(print()).andExpect(status().isOk());

        System.out.println("**************** Get All End ****************");
    }

    @Test
    public void add() throws Exception {
        System.out.println("**************** Add Started ****************");

        MultiValueMap<String, String> addEntity = getAddRecordDetails();
        this.mockMvc.perform(post(getEndPointUrl()).header(HttpHeaders.AUTHORIZATION, getBasicUserDigestHeaderValue())
                .params(addEntity)).andDo(print()).andExpect(status().isOk());
                
        System.out.println("**************** Add End ****************");
    }

    protected String getBasicAdminDigestHeaderValue() {
        return BASIC_ADMIN_DIGEST_HEADER_VALUE;
    }

    protected String getBasicUserDigestHeaderValue() {
        return BASIC_USER_DIGEST_HEADER_VALUE;
    }
}