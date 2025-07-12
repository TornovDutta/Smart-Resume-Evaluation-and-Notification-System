package org.example.hiring.service;


import jakarta.transaction.Transactional;
import org.example.hiring.DAO.JobSeekerRepo;
import org.example.hiring.DAO.RecruiterRepo;
import org.example.hiring.DAO.ResumeRepo;
import org.example.hiring.model.Recruiter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecruiterService {
    @Autowired
    private RecruiterRepo repo;

    @Autowired
    private ResumeRepo resume;

    @Autowired
    private JobSeekerRepo jobSeeker;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public ResponseEntity<Recruiter> saveUser(Recruiter user) {
        user.setUserName(user.getUserName());
        user.setPassword(encoder.encode(user.getPassword()));
        return new ResponseEntity<>(repo.save(user), HttpStatus.OK);
    }
    public ResponseEntity<List<Recruiter>> view(){
        return  new ResponseEntity<>(repo.findAll(),HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<String> delete() {
        try {

            if (jobSeeker != null) {
                jobSeeker.deleteAll();
                return new ResponseEntity<>("Delete all data ",HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Table is Empty", HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error occurred while deleting resume", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
