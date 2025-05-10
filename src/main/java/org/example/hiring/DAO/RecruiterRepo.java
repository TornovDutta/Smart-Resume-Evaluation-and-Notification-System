package org.example.hiring.DAO;

import org.example.hiring.model.Recruiter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecruiterRepo extends JpaRepository<Recruiter,Integer> {
    Recruiter findByUserName(String userName);
}
