package com.zemoso.greencommute.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class SavedJobId implements Serializable {

    @Column(name = "user_id")
    private int userId;

    @Column(name = "job_id")
    private int jobId;
}
