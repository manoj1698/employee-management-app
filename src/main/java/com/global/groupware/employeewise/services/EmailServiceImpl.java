package com.global.groupware.employeewise.services;

import com.global.groupware.employeewise.entities.Employee;
import com.global.groupware.employeewise.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Value("${spring.mail.username}")
    private String senderEmail;
    private final JavaMailSender javaMailSender;

    public EmailServiceImpl(JavaMailSender javaMailSender){
        this.javaMailSender=javaMailSender;
    }

    public void sendEmailToLevel1Manager(Employee employee) {
        String recipientEmail = findManagerEmailById(employee.getReportsTo());
        String subject = "New Employee Addition";
        String message = String.format(
                "%s will now work under you. Mobile number is %s and email is %s.",
                employee.getEmployeeName(),
                employee.getPhoneNumber(),
                employee.getEmail()
        );
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(recipientEmail);
        mailMessage.setSubject(subject);
        mailMessage.setText(message);
        mailMessage.setFrom(senderEmail);

        // Send the email
        javaMailSender.send(mailMessage);
    }

    private String findManagerEmailById(String managerId) {
        // Logic to retrieve manager's email by ID from the database
        Employee manager = employeeRepository.findById(managerId)
                .orElseThrow(() -> new RuntimeException("Manager not found"));
        return manager.getEmail();
    }
}

