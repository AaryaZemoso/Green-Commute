package com.zemoso.greencommute.service;

import com.zemoso.greencommute.entity.Job;
import com.zemoso.greencommute.entity.Skill;
import com.zemoso.greencommute.repository.JobRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@ExtendWith(MockitoExtension.class)
class JobServiceTests {

    @Mock
    JobRepository jobRepository;

    @InjectMocks
    JobService jobService = new JobServiceImpl();

    @Test
    void getJobByIdTest() {

        Job job = new Job(1, "ASE", "", "Hyderabad", null, null);
        Mockito.when(jobRepository.getById(1)).thenReturn(job);

        Job fetchedJob = jobService.getJobById(1);

        Assertions.assertThat(job.getId()).isSameAs(fetchedJob.getId());
    }

    @Test
    void getAllJobsTest() {

        List<Job> listOfJobs = new ArrayList<>();

        listOfJobs.add(new Job(1, "ASE", "", "Hyderabad", null, null));
        listOfJobs.add(new Job(2, "QA", "", "Hyderabad", null, null));

        Mockito.when(jobRepository.findAll()).thenReturn(listOfJobs);

        List<Job> fetchAllJobs = jobService.getAllJobs();

        Assertions.assertThat(fetchAllJobs).hasSize(2);
        Assertions.assertThat(fetchAllJobs.get(0).getId()).isSameAs(1);
        Assertions.assertThat(fetchAllJobs.get(1).getId()).isSameAs(2);
    }

    @Test
    void findByLocationTest() {
        List<Job> listOfJobs = new ArrayList<>();

        listOfJobs.add(new Job(1, "ASE", "", "Hyderabad", null, null));
        listOfJobs.add(new Job(2, "QA", "", "Hyderabad", null, null));

        Mockito.when(jobRepository.findByLocation("Hyderabad")).thenReturn(listOfJobs);

        List<Job> fetchAllJobs = jobService.findByLocation("Hyderabad");

        Assertions.assertThat(fetchAllJobs).hasSize(2);
        Assertions.assertThat(fetchAllJobs.get(0).getId()).isSameAs(1);
        Assertions.assertThat(fetchAllJobs.get(1).getId()).isSameAs(2);
    }

    @Test
    void findBySkillsTest() {
        List<Job> listOfJobs = new ArrayList<>();

        Skill java = new Skill(1, "Java");
        Skill aws = new Skill(2, "AWS");
        Skill r = new Skill(3, "R");

        listOfJobs.add(new Job(1, "ASE", "", "Hyderabad", null, Set.of(java, r)));
        listOfJobs.add(new Job(2, "QA", "", "Hyderabad", null, Set.of(aws, java)));

        String[] skills = new String[]{"Java"};

        Mockito.when(jobRepository.findBySkills(skills)).thenReturn(listOfJobs);

        List<Job> fetchAllJobs = jobService.findBySkills(skills);

        Assertions.assertThat(fetchAllJobs).hasSize(2);
        Assertions.assertThat(fetchAllJobs.get(0).getId()).isSameAs(1);
        Assertions.assertThat(fetchAllJobs.get(1).getId()).isSameAs(2);
    }

    @Test
    void findByLocationAndSkillsTest() {
        List<Job> listOfJobs = new ArrayList<>();

        Skill java = new Skill(1, "Java");
        Skill aws = new Skill(2, "AWS");
        Skill r = new Skill(3, "R");

        listOfJobs.add(new Job(1, "ASE", "", "Hyderabad", null, Set.of(java, r)));
        listOfJobs.add(new Job(2, "QA", "", "Hyderabad", null, Set.of(aws, java)));

        String[] skills = new String[]{"Java"};

        Mockito.when(jobRepository.findByLocationAndSkills("Hyderabad", skills)).thenReturn(listOfJobs);

        List<Job> fetchAllJobs = jobService.findByLocationAndSkills("Hyderabad", skills);

        Assertions.assertThat(fetchAllJobs).hasSize(2);
        Assertions.assertThat(fetchAllJobs.get(0).getId()).isSameAs(1);
        Assertions.assertThat(fetchAllJobs.get(1).getId()).isSameAs(2);
    }
}
