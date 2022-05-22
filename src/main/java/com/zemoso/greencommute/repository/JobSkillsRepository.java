package com.zemoso.greencommute.repository;

import com.zemoso.greencommute.entity.JobSkills;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface JobSkillsRepository extends JpaRepository<JobSkills, Integer> {
}
