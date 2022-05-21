package com.zemoso.greencommute.service;

import com.zemoso.greencommute.entity.User;

public interface UserService {

    User getUserById(int id);

    void addSaveJob(int userId, int jobId);

    void removeSaveJob(int userId, int jobId);
}
