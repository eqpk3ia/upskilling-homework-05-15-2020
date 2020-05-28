package com.sanket.todo.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.web.servlet.MockMvc;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class UserAccountControllerTest {
    @LocalServerPort
	private int port;

    @Autowired
	private MockMvc mockMvc;
    
    /*@Test
	public void addUser() throws Exception {
        System.out.println("**************** Add Users ****************");
        String basicDigestHeaderValue = "Basic " + new String(Base64.getEncoder().encode(("test@test.com:uzerPazz").getBytes()));
        this.mockMvc.perform(post("/ua/")
            .header(HttpHeaders.AUTHORIZATION, basicDigestHeaderValue)
            .param("fName", "Sanket")
            .param("lName", "Parekh")
            .param("email", "s.parekh@test.com")
            .param("pwd", "sparekh1"))
        .andDo(print()).andExpect(status().isOk());
        
        System.out.println("**************** Get Users Response Started ****************");
        //System.out.println(this.mockMvc.perform(get("/ua/")).andDo(print()));
        System.out.println("**************** Get Users Response End ****************");
				
    }
    
    @Test
	public void updateUser() throws Exception {
        System.out.println("**************** UPDATE Users ****************");

        this.mockMvc.perform(patch("/ua/1")
            .param("fName", "Sanket1")
            .param("lName", "Parekh1")
            .param("email", "Test@email1.com")
            .param("pwd", "sp1111"))
        .andDo(print()).andExpect(status().isOk());
        
        System.out.println("**************** Get Users Response Started ****************");
        System.out.println(this.mockMvc.perform(get("/ua/")).andDo(print()));
        System.out.println("**************** Get Users Response End ****************");
    }
    */
   
}