package com.example.building_company.service.impl;

import com.example.building_company.model.Client;
import com.example.building_company.repository.ClientRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.time.LocalDateTime;

@Service
@Slf4j
@AllArgsConstructor
public class EmailServiceImpl {

    private final ClientRepository clientRepository;
    private final JavaMailSender mailSender;
    private final TemplateEngine templateEngine;

    public void sendEmail(String to, String subject, String visitorPhoneNumber)  {
        saveNumberToDatabase(visitorPhoneNumber);
        try {
            sendEmailToOwner(to, subject, visitorPhoneNumber);
            log.info("Email was sent. {}", LocalDateTime.now());
        }catch (Exception e){
            log.warn("Email wasn't sent. {}", LocalDateTime.now());
        }
    }

    private void sendEmailToOwner(String to, String subject, String visitorPhoneNumber) throws MessagingException {
        log.warn("Number is: " + visitorPhoneNumber);
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
        mimeMessageHelper.setFrom("wymarzonydom.sender@gmail.com");
        mimeMessageHelper.setTo(to);
        mimeMessageHelper.setSubject(subject);
        Context context = new Context();
        context.setVariable("number", visitorPhoneNumber);
        String htmlContent = templateEngine.process("email", context);
        mimeMessageHelper.setText(htmlContent, true);
        mailSender.send(mimeMessage);
    }

    private void saveNumberToDatabase(String phoneNumber) {
        Client client = new Client();
        client.setPhoneNumber(phoneNumber);
        clientRepository.save(client);
    }
}
