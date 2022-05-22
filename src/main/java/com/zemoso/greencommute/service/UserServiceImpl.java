package com.zemoso.greencommute.service;

import com.zemoso.greencommute.entity.SavedJob;
import com.zemoso.greencommute.entity.SavedJobId;
import com.zemoso.greencommute.entity.User;
import com.zemoso.greencommute.exception.DataNotFoundException;
import com.zemoso.greencommute.repository.JobSkillsRepository;
import com.zemoso.greencommute.repository.SavedJobRepository;
import com.zemoso.greencommute.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.logging.Logger;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    SavedJobRepository savedJobRepository;


    @Override
    public User getUserById(int id) {
        return userRepository.getById(id);
    }

    @Override
    public void addSaveJob(int userId, int jobId) {
        savedJobRepository.save(new SavedJob(new SavedJobId(userId, jobId)));
    }

    @Override
    public void removeSaveJob(int userId, int jobId) {
        try {
            SavedJob savedJob = savedJobRepository.getById(new SavedJobId(userId, jobId));
            System.out.println(savedJob);
            savedJobRepository.delete(savedJob);
        } catch (EntityNotFoundException e) {
            throw new DataNotFoundException();
        }
    }
}
