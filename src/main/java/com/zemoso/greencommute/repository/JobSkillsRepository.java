package com.zemoso.greencommute.repository;

import com.zemoso.greencommute.entity.JobSkills;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface JobSkillsRepository extends JpaRepository<JobSkills, Integer> {

    @Query(value = "insert into job_skills values (?1, ?2)", nativeQuery = true)
    void saveByUserIdAndSkillId(int userId, int skillId);

    @Query(value = "delete from job_skills where user_id = ?1 and skill_id = ?2")
    void deleteByUserIdAndSkillId(int userId, int skillId);
}
