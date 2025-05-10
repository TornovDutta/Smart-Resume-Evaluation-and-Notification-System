package org.example.hiring.DAO;

import org.example.hiring.model.JobSeeker;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface JobSeekerRepo extends JpaRepository<JobSeeker, Integer> {
    JobSeeker findByName(String name);

    @Query("SELECT j FROM JobSeeker j ORDER BY j.ats DESC")
    List<JobSeeker> findTopNJobSeekersByAts(int limit);
}
