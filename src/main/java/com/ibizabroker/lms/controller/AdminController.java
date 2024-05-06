package com.ibizabroker.lms.controller;

import com.ibizabroker.lms.dao.UsersRepository;
import com.ibizabroker.lms.entity.EmailRequest;
import com.ibizabroker.lms.entity.Mail;
import com.ibizabroker.lms.entity.Role;
import com.ibizabroker.lms.entity.Users;
import com.ibizabroker.lms.exceptions.NotFoundException;
import com.ibizabroker.lms.service.MailService;
import com.ibizabroker.lms.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


import java.util.*;

@CrossOrigin("http://localhost:4200/")
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    private final MailService mailService;

    @Autowired // Injectez votre service UserService ici
    private UserService userService;


    public AdminController(MailService mailService) {
        this.mailService = mailService;
    }


    @PostMapping("/users")
    @PreAuthorize("hasRole('Admin')")
    public Users addUserByAdmin(@RequestBody Users user) {
        try {
            // Récupérer le rôle sélectionné de l'utilisateur
            Set<Role> roles = user.getRole();

            // Assurez-vous que le rôle sélectionné n'est pas vide
            if (roles.isEmpty()) {
                // Si aucun rôle sélectionné, vous pouvez définir un rôle par défaut ou gérer l'erreur selon votre logique métier
                return null; // ou lancez une exception, etc.
            }

            // Enregistrer l'utilisateur avec le rôle sélectionné dans la base de données
            String password = user.getPassword();
            String encryptPassword = passwordEncoder.encode(password);
            user.setPassword(encryptPassword);
            usersRepository.save(user);

            return user;
        } catch (Exception e) {
            // Gérer les erreurs d'ajout d'utilisateur, par exemple en lançant une exception ou en renvoyant un message d'erreur
            return null; // ou lancez une exception, etc.
        }
    }


    @GetMapping("/users")
    @PreAuthorize("hasRole('Admin')")
    public List<Users> getAllUsers() {
        return usersRepository.findAll();
    }

    @PreAuthorize("hasRole('Admin')")
    @GetMapping("/users/{id}")
    public ResponseEntity<Users> getUserById(@PathVariable Integer id) {
        Users user = usersRepository.findById(id).orElseThrow(() -> new NotFoundException("User with id "+ id +" does not exist."));
        return ResponseEntity.ok(user);
    }

    @PreAuthorize("hasRole('Admin')")
    @PutMapping("/users/{id}")
    public ResponseEntity<Users> updateUser(@PathVariable Integer id, @RequestBody Users userDetails) {
        Users user = usersRepository.findById(id).orElseThrow(() -> new NotFoundException("User with id "+ id +" does not exist."));

        user.setName(userDetails.getName());
        user.setRole(userDetails.getRole());
        user.setUsername(userDetails.getUsername());

        Users updatedUser = usersRepository.save(user);
        return ResponseEntity.ok(updatedUser);
    }

    @PreAuthorize("hasRole('Admin')")
    @DeleteMapping("/users/{id}")
    public ResponseEntity<Map<String, String>> deleteUser(@PathVariable Integer id) {
        try {
            usersRepository.deleteById(id);
            Map<String, String> response = new HashMap<>();
            response.put("message", "User deleted successfully");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, String> response = new HashMap<>();
            response.put("error", "Error deleting user: " + e.getMessage());
            return ResponseEntity.status(500).body(response);
        }
    }


    @PostMapping("/send-email")
    public ResponseEntity<?> sendEmail(@RequestBody EmailRequest emailRequest) {
        try {
            // Use the details from the emailRequest object
            String to = emailRequest.getTo();
            String subject = emailRequest.getSubject();
            String content = emailRequest.getContent();

            // Validate emailRequest fields if necessary

            // Implement your email sending logic here
            Mail mail = new Mail();
            mail.setMailTo(to);
            mail.setMailSubject(subject);
            mail.setMailContent(content);

            // Use the existing MailService for sending email
            mailService.sendEmail(mail);

            return ResponseEntity.ok("Email sent successfully");

        } catch (Exception e) {
            // Handle exceptions, log them, and return an appropriate response
            e.printStackTrace(); // Log the exception
            return ResponseEntity.status(500).body("Failed to send email");
        }
    }

  



}
