package org.example.hiring.controller;

import org.example.hiring.model.Recruiter;
import org.example.hiring.service.RecruiterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("recruiter")
public class RecruiterController {
    @Autowired
    private RecruiterService service;
    @PostMapping("add")
    public ResponseEntity<Recruiter> add(@RequestBody Recruiter user){
        return service.saveUser(user);
    }
    @GetMapping("viewAll")
    public ResponseEntity<List<Recruiter>> viewAll(){
        return service.view();
    }
    @DeleteMapping("delete")
    public ResponseEntity<String> deleteAll(){
        return service.delete();
    }
}
