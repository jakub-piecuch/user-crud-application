package com.kubapiecuch.springbootwithdatabase.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kubapiecuch.springbootwithdatabase.model.User;
import com.kubapiecuch.springbootwithdatabase.service.UserService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
@WebMvcTest(controllers = UserController.class)
@AutoConfigureMockMvc(addFilters = false)
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void shouldCreateUserAndReturnCreatedHttpResponse() throws Exception {
        //Arrange
        User user = new User(null, "John", "1234", "john@mail.com");
        User savedUser = new User(1L, "Jane", "1234", "jane@mail.com");
        ResponseEntity<User> responseEntity = new ResponseEntity<>(savedUser, HttpStatus.CREATED);
        when(userService.createUser(any())).thenReturn(savedUser);

        //Act
        ResultActions response = mockMvc.perform(
                MockMvcRequestBuilders.post("/v1/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user))
                );

        //Assert
        response.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(savedUser.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.username").value(savedUser.getUsername()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.password").value(savedUser.getPassword()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value(savedUser.getEmail()))
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(responseEntity.getBody())));
    }
}
