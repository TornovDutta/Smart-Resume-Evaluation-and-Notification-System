package org.example.hiring.service;

import org.apache.tika.Tika;
import org.apache.tika.exception.TikaException;

import org.example.hiring.DAO.ResumeRepo;
import org.example.hiring.model.Resume;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class ResumeService {
    @Autowired
    private ResumeRepo repo;

    private ChatClient chatClient;

    public ResumeService(OllamaChatModel chatModel) {
        this.chatClient = ChatClient.builder(chatModel).build();
    }

    public ResponseEntity<String> submit(String name, String email, MultipartFile file) {
        try {
            Resume resume = new Resume();
            resume.setName(name);
            resume.setEmail(email);
            resume.setFilename(file.getOriginalFilename());
            resume.setFiletype(file.getContentType());
            resume.setFiledata(file.getBytes());
            double ats=atsscore(file);
            resume.setAts(ats);

           repo.save(resume);

            return new ResponseEntity<>("Thank you " + name + " for submitting your resume", HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to process file", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    private double atsscore(MultipartFile file){
        try{
            String filedata=fileinfo(file);
            String jobDescription = "We are seeking a skilled Java Developer to build" +
                    " scalable applications using Spring Boot, REST APIs, and SQL. " +
                    "Collaborate with teams, write clean code, and deliver high-quality " +
                    "software solutions.";
            String prompt = "Rate the relevance of the following resume for the given job description on a scale from 0 to 100. Please provide only the score, nothing else.\n\n" +
                    "Job Description:\n" + jobDescription + "\n\n" +
                    "Resume:\n" + filedata + "\n\n" +
                    "Relevance Score:";
            String scoreStr = chatClient.prompt().user(prompt).call().content();
            double score;
            try {
                score = Double.parseDouble(scoreStr.replaceAll("[^\\d.]", ""));
            } catch (Exception e) {
                score = 0;
            }



            return score;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    private String fileinfo(MultipartFile file) {
        try {
            Tika tika = new Tika();
            String fileContent = tika.parseToString(file.getInputStream());
            return fileContent;
        } catch (IOException | TikaException e) {
            throw new RuntimeException("Failed to extract text from file", e);
        }
    }


    public ResponseEntity<String> delete(String name) {
        Resume resume=repo.findByName(name);
        if(resume!=null){
            repo.delete(resume);
            return new ResponseEntity<>(resume.getName()+" delete resume",HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>("Sorry your resume can't find",HttpStatus.OK);

    }
}
