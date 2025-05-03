package org.example.hiring.DAO;

import org.example.hiring.model.Resume;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ResumeRepo extends JpaRepository<Resume, Integer> {
    Resume findByName(String name);
}
