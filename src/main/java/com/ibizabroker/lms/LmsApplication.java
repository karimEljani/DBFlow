package com.ibizabroker.lms;

import java.util.HashSet;
import java.util.Set;

import com.ibizabroker.lms.entity.Mail;
import com.ibizabroker.lms.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.ibizabroker.lms.dao.UsersRepository;
import com.ibizabroker.lms.entity.Role;
import com.ibizabroker.lms.entity.Users;

@SpringBootApplication
public class LmsApplication {
    private MailService mailService;
    @Autowired
    public LmsApplication(MailService mailService) {
        this.mailService = mailService;
    }

	public static void main(String[] args) {
		SpringApplication.run(LmsApplication.class, args);
	}
    @Autowired

    @Bean
    CommandLineRunner addAdminUser(UsersRepository usersRepository, BCryptPasswordEncoder passwordEncoder) {
        return args -> {
            Users adminUser = new Users();
            adminUser.setUsername("jani");
            adminUser.setName("jani");

            // Setting up roles for the admin user
            Role adminRole = new Role();
            adminRole.setRoleName("Admin");
            Set<Role> adminRoles = new HashSet<>();
            adminRoles.add(adminRole);

            adminUser.setRole(adminRoles);

            // Setting up password for the admin user
            String adminPassword = "123";
            String encryptedAdminPassword = passwordEncoder.encode(adminPassword);
            adminUser.setPassword(encryptedAdminPassword);

            // Save the admin user to the repository
            usersRepository.save(adminUser);
        };
    }

    //@Override
    //public void run(String... args) throws Exception
    //{
    //    Mail mail = new Mail();
    //    mail.setMailFrom("karimsuslu@gmail.com");
    //    mail.setMailTo("karimsuslu@gmail.com");
    //    mail.setMailSubject("Spring Boot - Email demo");
    //    mail.setMailContent("Ramathane a7liwaaaaaaaaaaaaaat");
    //    mailService.sendEmail(mail);
    //}

}
