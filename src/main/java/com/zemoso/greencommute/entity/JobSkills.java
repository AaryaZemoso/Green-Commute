package com.zemoso.greencommute.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "job_skills")
public class JobSkills {

    @Id
    @Column(name = "job_id")
    private int jobId;

    @Id
    @Column(name = "skill_id")
    private int skillId;
}
