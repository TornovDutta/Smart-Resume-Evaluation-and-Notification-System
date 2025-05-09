package org.example.hiring.service;

import org.example.hiring.DAO.JobSheekerRepo;
import org.example.hiring.DAO.ResumeRepo;
import org.example.hiring.model.Resume;
import org.example.hiring.model.JobSheeker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShortListService {

    @Autowired
    private JobSheekerRepo jobSheekerRepo;

    @Autowired
    private ResumeRepo resumeRepo;

    public ResponseEntity<List<Resume>> getAll() {
        try {
            List<Resume> resumes = resumeRepo.findAll();
            return new ResponseEntity<>(resumes, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    public ResponseEntity<Resume> getResume(String name) {

        JobSheeker jobSheeker = jobSheekerRepo.findByName(name);

        if (jobSheeker != null) {
            Resume resume=jobSheeker.getResume();

            return new ResponseEntity<>(resume, HttpStatus.OK);
        } else {

            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}
