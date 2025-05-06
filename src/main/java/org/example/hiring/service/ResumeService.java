package org.example.hiring.service;

import org.apache.tika.Tika;
import org.apache.tika.exception.TikaException;
import org.example.hiring.DAO.JobSheekerRepo;
import org.example.hiring.DAO.ResumeRepo;
import org.example.hiring.model.JobSheeker;
import org.example.hiring.model.Resume;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jakarta.transaction.Transactional;
import java.io.IOException;

@Service
public class ResumeService {

    @Autowired
    private ResumeRepo resumeRepo;

    @Autowired
    private JobSheekerRepo jobSheekerRepo;

    private final ChatClient chatClient;

    public ResumeService(OllamaChatModel chatModel) {
        this.chatClient = ChatClient.builder(chatModel).build();
    }

    public ResponseEntity<String> submit(String name, String email, MultipartFile file) {
        if (file.isEmpty()) {
            return new ResponseEntity<>("Uploaded file is empty", HttpStatus.BAD_REQUEST);
        }

        try {
            Resume resume = new Resume();
            resume.setFilename(file.getOriginalFilename());
            resume.setFiletype(file.getContentType());
            resume.setFiledata(file.getBytes());

            double ats = atsscore(file);

            JobSheeker jobSheeker = new JobSheeker();
            jobSheeker.setName(name);
            jobSheeker.setEmail(email);
            jobSheeker.setResume(resume);
            jobSheeker.setAts(ats);

            jobSheekerRepo.save(jobSheeker);

            return new ResponseEntity<>("Thank you " + name + " for submitting your resume", HttpStatus.ACCEPTED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Failed to process file", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private double atsscore(MultipartFile file) {
        try {
            String filedata = extractFileContent(file);

            String jobDescription = "We are seeking a skilled Java Developer to build " +
                    "scalable applications using Spring Boot, REST APIs, and SQL. " +
                    "Collaborate with teams, write clean code, and deliver high-quality software solutions.";

            String prompt = "Rate the relevance of the following resume for the given job description on a scale from 0 to 100. " +
                    "Please provide only the score, nothing else.\n\n" +
                    "Job Description:\n" + jobDescription + "\n\n" +
                    "Resume:\n" + filedata + "\n\n" +
                    "Relevance Score:";

            String scoreStr = chatClient.prompt().user(prompt).call().content().trim();

            double score = Double.parseDouble(scoreStr.split("\\s+")[0]);
            return score;
        } catch (Exception e) {
            e.printStackTrace();
            return 0.0;
        }
    }

    private String extractFileContent(MultipartFile file) {
        try {
            Tika tika = new Tika();
            return tika.parseToString(file.getInputStream());
        } catch (IOException | TikaException e) {
            throw new RuntimeException("Failed to extract text from file", e);
        }
    }

    @Transactional
    public ResponseEntity<String> delete(String name) {
        try {
            JobSheeker jobSheeker = jobSheekerRepo.findByName(name);
            if (jobSheeker != null) {
                jobSheekerRepo.deleteById(jobSheeker.getId());
                return new ResponseEntity<>(name + "'s resume deleted", HttpStatus.ACCEPTED);
            } else {
                return new ResponseEntity<>("Sorry, resume for " + name + " not found", HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error occurred while deleting resume", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
