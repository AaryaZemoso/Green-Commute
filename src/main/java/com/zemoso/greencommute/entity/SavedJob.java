package com.zemoso.greencommute.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_saved_jobs")
public class SavedJob {

    @EmbeddedId
    private SavedJobId id;
}
