package com.zemoso.greencommute.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@IdClass(JobSkillsId.class)
@Table(name = "job_skills")
public class JobSkills implements Serializable {

    @Id
    @Column(name = "job_id")
    private int jobId;

    @Id
    @Column(name = "skill_id")
    private int skillId;
}

@Data
@NoArgsConstructor
@AllArgsConstructor
class JobSkillsId implements Serializable {
    private int jobId;
    private int skillId;
}