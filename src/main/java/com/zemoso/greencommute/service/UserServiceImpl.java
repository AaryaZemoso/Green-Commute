package com.zemoso.greencommute.service;

import com.zemoso.greencommute.entity.User;
import com.zemoso.greencommute.repository.JobSkillsRepository;
import com.zemoso.greencommute.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;

    @Autowired
    JobSkillsRepository jobSkillsRepository;

    @Override
    public User getUserById(int id) {
        return userRepository.getById(id);
    }

    @Override
    public void addSaveJob(int userId, int jobId) {
        jobSkillsRepository.saveByUserIdAndSkillId(userId, jobId);
    }

    @Override
    public void removeSaveJob(int userId, int jobId) {
        jobSkillsRepository.deleteByUserIdAndSkillId(userId, jobId);
    }
}
