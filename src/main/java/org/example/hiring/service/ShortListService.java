package org.example.hiring.service;

import org.example.hiring.DAO.ResumeRepo;
import org.example.hiring.model.Resume;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class ShortListService {

    @Autowired
    private ResumeRepo repo;
    public ResponseEntity<List<Resume>> getAll() {
       try{
           return new ResponseEntity<>(repo.findAll(), HttpStatus.OK);
       }catch(Exception e){
           return new ResponseEntity<>(null,HttpStatus.OK);
       }
    }

    public ResponseEntity<Resume> getResume(String name) {
        if(repo.findByName(name)!=null){

            return new ResponseEntity<>(repo.findByName(name),HttpStatus.OK);
        }else{
            return new ResponseEntity<>(null,HttpStatus.OK);
        }
    }
}
