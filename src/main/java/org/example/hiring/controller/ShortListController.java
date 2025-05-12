package org.example.hiring.controller;


import org.example.hiring.model.JobSeeker;
import org.example.hiring.model.Resume;
import org.example.hiring.service.ShortListService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("hire")
public class ShortListController {
    @Autowired
    private ShortListService service;
    @GetMapping("checkAll")
    public ResponseEntity<List<JobSeeker>> getAll(){
        return service.getAll();
    }
    @GetMapping("checkAllJobsheeker")
    public ResponseEntity<List<JobSeeker>> getAllJobSheeker(){
        return service.getAllJobsheeker();
    }
    @PostMapping("resume/{name}")
    public ResponseEntity<Resume> getResume(@PathVariable String name){
        return service.getResume(name);
    }
}
