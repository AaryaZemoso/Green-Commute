package com.zemoso.greencommute.controller;

import com.zemoso.greencommute.dto.JobDTO;
import com.zemoso.greencommute.dto.ResponseDTO;
import com.zemoso.greencommute.dto.UserDTO;
import com.zemoso.greencommute.entity.SavedJob;
import com.zemoso.greencommute.entity.SavedJobId;
import com.zemoso.greencommute.entity.User;
import com.zemoso.greencommute.repository.SavedJobRepository;
import com.zemoso.greencommute.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable(value = "id") int id) {
        User user = userService.getUserById(id);
        if (user == null) {
            throw new ResourceNotFoundException("Cannot find user with id : " + id);
        }
        return ResponseEntity.ok().body(UserDTO.toDTO(user));
    }

    @GetMapping("/{id}/savedJobs")
    public List<JobDTO> getAllSavedJobsOfUser(@PathVariable(value = "id") int id) {
        User user = userService.getUserById(id);
        if (user == null) {
            throw new ResourceNotFoundException("Cannot find user with id : " + id);
        }
        return JobDTO.toListDTO(user.getSavedJobs().stream().collect(Collectors.toList()));
    }

    @PostMapping("/{user_id}/savedJobs/{job_id}")
    public ResponseEntity<ResponseDTO> addSavedJobToUser(@PathVariable("user_id") int userId, @PathVariable("job_id") int jobId) {
        userService.addSaveJob(userId, jobId);
        return new ResponseEntity<ResponseDTO>(new ResponseDTO("Success", "Added job with id : " + jobId + " into user " + userId + " saved jobs list"), HttpStatus.CREATED);
    }

    @DeleteMapping("/{user_id}/savedJobs/{job_id}")
    public ResponseEntity<ResponseDTO> removeSavedJobFromUser(@PathVariable("user_id") int userId, @PathVariable("job_id") int jobId) {
        userService.removeSaveJob(userId, jobId);
        return new ResponseEntity<ResponseDTO>(new ResponseDTO("Success", "Removed job with id : " + jobId + " from user " + userId + " saved jobs list"), HttpStatus.CREATED);
    }
}
