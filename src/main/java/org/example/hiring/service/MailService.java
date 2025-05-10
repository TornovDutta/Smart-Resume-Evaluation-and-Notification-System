package org.example.hiring.service;


import org.example.hiring.DAO.JobSheekerRepo;

import org.example.hiring.model.JobSheeker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MailService {
    @Value("${email.from}")
    private String from;

    @Autowired
    private JavaMailSender sender;

    @Autowired
    private JobSheekerRepo repo;

    @Transactional
    public ResponseEntity<String> send(int num) {
        long rows = repo.count();

        if (rows >= num) {
            List<JobSheeker> jobSeekers = repo.findTopNJobSeekersByAts(num);

            for (JobSheeker jobSeeker : jobSeekers) {
                String toEmail = jobSeeker.getEmail();
                String name = jobSeeker.getName();

                try {
                    SimpleMailMessage msg = new SimpleMailMessage();
                    msg.setTo(toEmail);
                    msg.setFrom(from);
                    msg.setSubject("Offer Letter");
                    msg.setText("Dear " + name + ",\n\nCongratulations! You have been selected.");

                    sender.send(msg);
                } catch (Exception e) {

                    return new ResponseEntity<>("Failed to send email to: " + toEmail, HttpStatus.INTERNAL_SERVER_ERROR);
                }
            }

            return new ResponseEntity<>("Emails sent to " + num + " candidates.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Not enough candidates in the database", HttpStatus.NOT_FOUND);
        }
    }

    @Transactional
    public ResponseEntity<String> sendOfferToCandidateByName(String name) {
        JobSheeker jobSeeker = repo.findByName(name);
        System.out.println(jobSeeker);


        String toEmail = jobSeeker.getEmail();
        System.out.println(toEmail);


        try {
            SimpleMailMessage msg = new SimpleMailMessage();
            msg.setTo(toEmail);
            msg.setFrom(from);
            msg.setSubject("Offer Letter");
            msg.setText("Dear " + name + ",\n\nCongratulations! You have been selected.");

            sender.send(msg);
            return new ResponseEntity<>("Email sent to " + name, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to send email to: " + toEmail, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
