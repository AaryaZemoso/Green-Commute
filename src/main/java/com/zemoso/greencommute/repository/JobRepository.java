package com.zemoso.greencommute.repository;

import com.zemoso.greencommute.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<Job, Integer> {

    List<Job> findByLocation(String location);

    @Query(value = "select distinct job.* from job left join job_skills on job.id = job_skills.job_id left join skill on job_skills.skill_id = skill.id where skill.name in (:1)", nativeQuery = true)
    List<Job> findBySkills(String[] skills);

    @Query(value = "select distinct job.* from job left join job_skills on job.id = job_skills.job_id left join skill on job_skills.skill_id = skill.id where skill.name in (:1) and location = ?2", nativeQuery = true)
    List<Job> findByLocationAndSkills(String location, String[] skills);
}
