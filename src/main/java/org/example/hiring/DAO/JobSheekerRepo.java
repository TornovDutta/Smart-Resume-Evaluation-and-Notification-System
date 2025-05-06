package org.example.hiring.DAO;

import org.example.hiring.model.JobSheeker;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JobSheekerRepo extends JpaRepository<JobSheeker, Integer> {
    JobSheeker findByName(String name);

    @Query("SELECT j FROM JobSheeker j ORDER BY j.ats DESC")
    List<JobSheeker> findTopNJobSeekersByAts(int limit);
}
