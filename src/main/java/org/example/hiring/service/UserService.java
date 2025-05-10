package org.example.hiring.service;


import org.example.hiring.DAO.RecruiterRepo;
import org.example.hiring.model.Recruiter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private RecruiterRepo repo;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public Recruiter saveUser(Recruiter user) {
        user.setPassword(encoder.encode(user.getPassword()));
        return repo.save(user);
    }
    public List<Recruiter> view(){
        return  repo.findAll();
    }
}
