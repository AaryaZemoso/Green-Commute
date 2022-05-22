package com.zemoso.greencommute.controller;

import com.zemoso.greencommute.dto.JobDTO;
import com.zemoso.greencommute.entity.Job;
import com.zemoso.greencommute.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobs")
public class JobController {

    @Autowired
    JobService jobService;

    @GetMapping({"/", ""})
    public List<JobDTO> getAllJobs(@RequestParam(name = "location", required = false) String location, @RequestParam(name = "skills", required = false) String skills) {

        String listOfSkills[] = null;

        if (skills != null) {
            listOfSkills = skills.split(",");
        }

        if (location != null && skills != null) {       // If both the location and skills are provided
            return JobDTO.toListDTO(jobService.findByLocationAndSkills(location, listOfSkills));
        } else if (skills != null) {                    // If only skills is provided
            return JobDTO.toListDTO(jobService.findBySkills(listOfSkills));
        } else if (location != null) {                  // If only location is provided
            return JobDTO.toListDTO(jobService.findByLocation(location));
        }
        return JobDTO.toListDTO(jobService.getAllJobs());   // Else return all jobs
    }

    @GetMapping("/{id}")
    public JobDTO getJobById(@PathVariable("id") int id) {
        return JobDTO.toDTO(jobService.getJobById(id));
    }
}
