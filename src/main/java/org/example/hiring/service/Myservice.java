package org.example.hiring.service;


import org.example.hiring.DAO.RecruiterRepo;
import org.example.hiring.model.Recruiter;
import org.example.hiring.model.RecruiterPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class Myservice implements UserDetailsService {
    @Autowired
    private RecruiterRepo repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Recruiter users = repo.findByUserName(username);
        if(users ==null){
            System.out.println("User 404");
            throw new UsernameNotFoundException("sorry"+username);
        }
        return new RecruiterPrincipal(users);
    }
}
