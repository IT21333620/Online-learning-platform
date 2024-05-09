package com.olp.notificationservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.olp.notificationservice.model.EmailRequest;
import com.olp.notificationservice.service.EmailSenderService;

@RestController
@RequestMapping("/api")
public class EmailController {

    @Autowired
    private EmailSenderService emailSenderService;

    @PostMapping("/send-email")
    public String sendEmail(@RequestBody EmailRequest emailRequest) {
        emailSenderService.sendEmail(emailRequest.getToEmail(), emailRequest.getSubject(), emailRequest.getBody());
        return "Email sent successfully!";
    }

}
