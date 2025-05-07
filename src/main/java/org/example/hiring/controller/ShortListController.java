package org.example.hiring.controller;

import org.example.hiring.model.JobSheeker;
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
    public ResponseEntity<List<Resume>> getAll(){
        return service.getAll();
    }
    @PostMapping("resume/{name}")
    public ResponseEntity<JobSheeker> getResume(@PathVariable String name){
        return service.getResume(name);
    }
}
