package org.example.hiring.service;


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

    public ResponseEntity<String> delete() {
        try{
            resume.deleteAll();
            jobSeeker.deleteAll();
            return new ResponseEntity<>("Delete all data",HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
