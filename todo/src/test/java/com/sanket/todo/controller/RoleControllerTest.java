package com.sanket.todo.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class RoleControllerTest {
    @LocalServerPort
	private int port;

    @Autowired
	private MockMvc mockMvc;
    
    @Test
	public void getRoles() throws Exception {
        String URL = "/roles/";
        System.out.println("**************** Get Roles ****************");
        this.mockMvc.perform(get(URL)).andDo(print()).andExpect(status().isOk());
        
        System.out.println("**************** Get Rules Response Started ****************");
        System.out.println(this.mockMvc.perform(get(URL)).andDo(print()));
        System.out.println("**************** Get Rules Response End ****************");
				
	}
}