package com.zemoso.greencommute.repository;

import com.zemoso.greencommute.entity.SavedJob;
import com.zemoso.greencommute.entity.SavedJobId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SavedJobRepository extends JpaRepository<SavedJob, SavedJobId> {
}