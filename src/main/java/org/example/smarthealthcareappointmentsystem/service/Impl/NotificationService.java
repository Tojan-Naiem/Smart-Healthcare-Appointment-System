package org.example.smarthealthcareappointmentsystem.service.Impl;

import org.example.smarthealthcareappointmentsystem.dto.AppointmentDTO;
import org.example.smarthealthcareappointmentsystem.entity.Patient;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
    private JavaMailSender javaMailSender;
    public NotificationService(JavaMailSender javaMailSender){
        this.javaMailSender=javaMailSender;
    }
    public void sendEmail(String email){
        SimpleMailMessage mail=new SimpleMailMessage();
        mail.setTo(email);
        mail.setFrom("tojan050@gmail.com");
        mail.setText("A new appointment has been subscribed to you! Check your portal.");
        mail.setSubject("New appointment has been scheduled!");
        try {
            javaMailSender.send(mail);
        } catch (MailException e) {
            e.printStackTrace();
        }

    }
}
