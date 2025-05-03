package org.example.hiring.controller;

import org.example.hiring.service.ResumeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("jobsheeker")
public class ResumerSubmitController {
    @Autowired
    private ResumeService resumeService;
    @PostMapping("submit")
    public ResponseEntity<String> submit(@RequestParam String name, @RequestParam String email,
                                         @RequestParam MultipartFile file) {
        return resumeService.submit(name, email, file);
    }
    @PutMapping("update")
    public ResponseEntity<String> update(@RequestParam String name, @RequestParam String email,
                                         @RequestParam MultipartFile file) {
        return resumeService.submit(name, email, file);
    }
    @DeleteMapping("delete/{name}")
    public ResponseEntity<String> delete(@PathVariable String name){
        return resumeService.delete(name);
    }
}
