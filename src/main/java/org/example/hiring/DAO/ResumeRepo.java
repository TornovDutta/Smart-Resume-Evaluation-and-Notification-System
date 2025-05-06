package org.example.hiring.DAO;

import org.example.hiring.model.Resume;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ResumeRepo extends JpaRepository<Resume, Integer> {
    Resume findByName(String name);
    @Query("SELECT COUNT(r) FROM Resume r")
    long numofRow();


}
