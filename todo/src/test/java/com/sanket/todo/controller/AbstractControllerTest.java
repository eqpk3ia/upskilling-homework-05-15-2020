package com.sanket.todo.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Base64;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.servlet.MockMvc;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public abstract class AbstractControllerTest {

    private static String BASIC_ADMIN_DIGEST_HEADER_VALUE = "Basic "
            + new String(Base64.getEncoder().encode(("admin@Test.com:pwd").getBytes()));

    private static String BASIC_USER_DIGEST_HEADER_VALUE = "Basic "
            + new String(Base64.getEncoder().encode(("user@Test.com:pwd").getBytes()));

    @LocalServerPort
    protected int port;
    @Autowired
    protected MockMvc mockMvc;

    public abstract String getEndPointUrl();

    @Test
    public void getAll() throws Exception {

        System.out.println("**************** Get All ****************");

        this.mockMvc.perform(get(getEndPointUrl()).header(HttpHeaders.AUTHORIZATION, getBasicAdminDigestHeaderValue()))
                .andDo(print()).andExpect(status().isOk());

        System.out.println("**************** Get All End ****************");
    }

    protected String getBasicAdminDigestHeaderValue() {
        return BASIC_ADMIN_DIGEST_HEADER_VALUE;
    }

    protected String getBasicUserDigestHeaderValue() {
        return BASIC_USER_DIGEST_HEADER_VALUE;
    }
}