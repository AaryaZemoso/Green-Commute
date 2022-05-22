package com.zemoso.greencommute.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zemoso.greencommute.entity.*;
import com.zemoso.greencommute.repository.SavedJobRepository;
import com.zemoso.greencommute.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashSet;
import java.util.Set;

@ExtendWith(MockitoExtension.class)
class UserControllerTests {

    @Mock
    UserService userService;

    @InjectMocks
    UserController userController;

    @Autowired
    MockMvc mockMvc;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    void getUserByIdSuccessTest() throws Exception{

        User user = new User(1, "Aarya", Set.of());
        Mockito.when(userService.getUserById(1)).thenReturn(user);

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .get("/api/users/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(asJsonString(user))
                ).andDo(MockMvcResultHandlers.print());

        Mockito.verify(userService).getUserById(1);
    }

    @Test
    void getUserByIdFailureTest() throws Exception {
        Mockito.when(userService.getUserById(1)).thenReturn(null);

        mockMvc.perform(
                MockMvcRequestBuilders
                        .get("/api/users/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(null))
                )
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(result -> Assertions.assertTrue(result.getResolvedException() instanceof ResourceNotFoundException));
    }

    @Test
    void getAllSavedJobsOfUserSuccessTest() throws Exception{
        User user = new User(1, "Aarya", Set.of(new Job(1, "ASE", "", "", new Company(1, "", new HashSet<>()), new HashSet<>())));
        Mockito.when(userService.getUserById(1)).thenReturn(user);

        mockMvc.perform(
                MockMvcRequestBuilders
                        .get("/api/users/1/savedJobs")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(user))
        ).andDo(MockMvcResultHandlers.print());

        Mockito.verify(userService).getUserById(1);
    }

    @Test
    void getAllSavedJobsOfUserFailureTest() throws Exception{
        Mockito.when(userService.getUserById(1)).thenReturn(null);

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .get("/api/users/1/savedJobs")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(asJsonString(null))
                )
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(result -> Assertions.assertTrue(result.getResolvedException() instanceof ResourceNotFoundException));
    }

    @Test
    void addSavedJobToUserTest() throws Exception {

        Mockito.doNothing().when(userService).addSaveJob(1, 1);

        mockMvc.perform(
                MockMvcRequestBuilders
                        .post("/api/users/1/savedJobs/1")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andDo(MockMvcResultHandlers.print());

        Mockito.verify(userService).addSaveJob(1, 1);
    }

    @Test
    void removeSavedJobFromUserTest() throws Exception {

        Mockito.doNothing().when(userService).removeSaveJob(1, 1);

        mockMvc.perform(
                MockMvcRequestBuilders
                        .delete("/api/users/1/savedJobs/1")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andDo(MockMvcResultHandlers.print());

        Mockito.verify(userService).removeSaveJob(1, 1);
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
