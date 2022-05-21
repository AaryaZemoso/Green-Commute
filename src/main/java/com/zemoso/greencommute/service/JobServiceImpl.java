package com.zemoso.greencommute.service;

import com.zemoso.greencommute.entity.Job;
import com.zemoso.greencommute.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobServiceImpl implements JobService{

    @Autowired
    JobRepository jobRepository;

    @Override
    public Job getJobById(int id) {
        return jobRepository.getById(id);
    }

    @Override
    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }

    @Override
    public List<Job> findByLocation(String location) {
        return jobRepository.findByLocation(location);
    }

    @Override
    public List<Job> findBySkills(String[] skills) {
        return jobRepository.findBySkills(skills);
    }

    @Override
    public List<Job> findByLocationAndSkills(String location, String[] skills){
        return jobRepository.findByLocationAndSkills(location, skills);
    }
}
