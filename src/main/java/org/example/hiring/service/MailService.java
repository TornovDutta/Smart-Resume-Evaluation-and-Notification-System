package org.example.hiring.service;


import org.example.hiring.DAO.JobSheekerRepo;

import org.example.hiring.model.JobSheeker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MailService {
    private static final String FROM = "tornovdutta@gmail.com";

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
                    msg.setFrom(FROM);
                    msg.setSubject("Offer Letter");
                    msg.setText("Dear " + name + ",\n\nCongratulations! You have been selected.");

                    sender.send(msg);
                } catch (Exception e) {
                    // Log this exception for better tracking
                    return new ResponseEntity<>("Failed to send email to: " + toEmail, HttpStatus.INTERNAL_SERVER_ERROR);
                }
            }

            return new ResponseEntity<>("Emails sent to " + num + " candidates.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Not enough candidates in the database", HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<String> sendOfferToCandidateByName(String name) {
        JobSheeker jobSeeker = repo.findByName(name);

        if (jobSeeker != null) {
            String toEmail = jobSeeker.getEmail();
            String toName = jobSeeker.getName();

            try {
                SimpleMailMessage msg = new SimpleMailMessage();
                msg.setTo(toEmail);
                msg.setFrom(FROM);
                msg.setSubject("Offer Letter");
                msg.setText("Dear " + toName + ",\n\nCongratulations! You have been selected.");

                sender.send(msg);
                return new ResponseEntity<>("Email sent to " + toName, HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>("Failed to send email to: " + toEmail, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<>("Candidate with name " + name + " not found", HttpStatus.NOT_FOUND);
        }
    }
}
