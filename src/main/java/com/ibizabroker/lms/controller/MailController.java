package com.ibizabroker.lms.controller;

import com.ibizabroker.lms.entity.Mail;
import com.ibizabroker.lms.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/users") // Endpoint pour les utilisateurs administrateurs
public class MailController {

    private final MailService mailService;

    @Autowired
    public MailController(MailService mailService) {
        this.mailService = mailService;
    }

    @PostMapping("/sendemail") // Endpoint pour envoyer un e-mail
    public ResponseEntity<String> sendEmail(@RequestBody Mail mail) {
        try {
            mailService.sendEmail(mail); // Utilisation du service de messagerie pour envoyer l'e-mail
            return new ResponseEntity<>("E-mail sent successfully", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Failed to send e-mail", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}