package org.example.hiring.controller;

import org.example.hiring.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("mail")
public class MailController {

    @Autowired
    private MailService service;

    @PostMapping("send/{num}")
    public ResponseEntity<String> sendMail(@PathVariable int num) {
        return service.send(num);
    }
    @PostMapping("send/{name}")
    public ResponseEntity<String> sendMail(@PathVariable String  name) {
        return service.sendOfferToCandidateByName(name);
    }
}
