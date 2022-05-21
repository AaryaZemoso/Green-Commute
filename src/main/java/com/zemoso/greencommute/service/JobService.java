package com.zemoso.greencommute.service;

import com.zemoso.greencommute.entity.Job;

import java.util.List;

public interface JobService {
    Job getJobById(int id);
    List<Job> getAllJobs();
    List<Job> findByLocation(String location);

    List<Job> findBySkills(String[] skills);

    List<Job> findByLocationAndSkills(String location, String[] skills);
}
