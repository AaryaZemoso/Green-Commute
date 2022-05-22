package com.zemoso.greencommute.controller;

import com.zemoso.greencommute.entity.Company;
import com.zemoso.greencommute.entity.Job;
import com.zemoso.greencommute.exception.DataNotFoundException;
import com.zemoso.greencommute.service.JobService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class JobControllerTests {

    @Mock
    JobService jobService;

    @InjectMocks
    JobController jobController;

    GlobalExceptionHandler globalExceptionHandler;

    @Autowired
    MockMvc mockMvc;

    @BeforeEach
    void setup() {
        globalExceptionHandler = new GlobalExceptionHandler();

        mockMvc = MockMvcBuilders
                .standaloneSetup(jobController)
                .setControllerAdvice(globalExceptionHandler)
                .build();
    }

    @Test
    void getAllJobsTest() throws Exception {
        Mockito.when(jobService.getAllJobs()).thenReturn(List.of(new Job(1, "", "", "", new Company(1, "", new HashSet<>()), new HashSet<>())));

        mockMvc.perform(
                MockMvcRequestBuilders
                        .get("/api/jobs")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andDo(MockMvcResultHandlers.print());

        Mockito.verify(jobService).getAllJobs();
    }

    @Test
    void getJobByIdTest() throws Exception{
        Mockito.when(jobService.getJobById(1)).thenReturn(new Job(1, "", "", "", new Company(1, "", new HashSet<>()), new HashSet<>()));

        mockMvc.perform(
                MockMvcRequestBuilders
                        .get("/api/jobs/1")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andDo(MockMvcResultHandlers.print());

        Mockito.verify(jobService).getJobById(1);
    }

    @Test
    void getJobByIdExceptionTest() throws Exception{
        Mockito.when(jobService.getJobById(1)).thenThrow(new RuntimeException());

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .get("/api/jobs/1")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(MockMvcResultMatchers.status().is(500))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void getJobByIdDataNotFoundExceptionTest() throws Exception{
        Mockito.when(jobService.getJobById(1)).thenThrow(new DataNotFoundException());

        mockMvc.perform(
                MockMvcRequestBuilders
                        .get("/api/jobs/1")
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(MockMvcResultMatchers.status().is(404))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void getJobsByLocation() throws Exception {

        Job job = new Job(1, "", "", "", new Company(1, "", new HashSet<>()), new HashSet<>());
        ArrayList<Job> list = new ArrayList<>();
        list.add(job);

        Mockito.when(jobService.findByLocation("Hyderabad")).thenReturn(list);

        mockMvc.perform(
                MockMvcRequestBuilders
                        .get("/api/jobs?location=Hyderabad")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andDo(MockMvcResultHandlers.print());

        Mockito.verify(jobService).findByLocation("Hyderabad");
    }

    @Test
    void getJobsBySkills() throws Exception {

        Job job = new Job(1, "", "", "", new Company(1, "", new HashSet<>()), new HashSet<>());
        ArrayList<Job> list = new ArrayList<>();
        list.add(job);

        String[] skillParam = new String[]{"C", "Java"};

        Mockito.when(jobService.findBySkills(skillParam)).thenReturn(list);

        mockMvc.perform(
                MockMvcRequestBuilders
                        .get("/api/jobs?skills=C,Java")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andDo(MockMvcResultHandlers.print());

        Mockito.verify(jobService).findBySkills(skillParam);
    }

    @Test
    void getJobsByLocationAndSkills() throws Exception {

        Job job = new Job(1, "", "", "", new Company(1, "", new HashSet<>()), new HashSet<>());
        ArrayList<Job> list = new ArrayList<>();
        list.add(job);

        String[] skillParam = new String[]{"C", "Java"};

        Mockito.when(jobService.findByLocationAndSkills("Hyderabad", skillParam)).thenReturn(list);

        mockMvc.perform(
                MockMvcRequestBuilders
                        .get("/api/jobs?location=Hyderabad&skills=C,Java")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andDo(MockMvcResultHandlers.print());

        Mockito.verify(jobService).findByLocationAndSkills("Hyderabad", skillParam);
    }
}
