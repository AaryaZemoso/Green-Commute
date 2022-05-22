package com.zemoso.greencommute.dto;

import com.zemoso.greencommute.entity.Job;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
public class JobDTO implements Serializable {
    private final int id;
    private final String name;
    private final String location;
    private final String companyName;
    private final Set<String> skills;
    private final Set<String> routes;

    public static JobDTO toDTO(Job job) {
        return new JobDTO(
                job.getId(),
                job.getName(),
                job.getLocation(),
                job.getCompany().getName(),
                job.getSkills().stream().map(element -> element.getName()).collect(Collectors.toSet()),
                job.getCompany().getRoutes().stream().map(element -> element.getType()).collect(Collectors.toSet())
        );
    }

    public static List<JobDTO> toListDTO(List<Job> jobs) {
        return jobs.stream().map(JobDTO::toDTO).collect(Collectors.toList());
    }
}
