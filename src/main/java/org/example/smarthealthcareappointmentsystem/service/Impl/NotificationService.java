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
    public void sendEmail(Patient patient){
        SimpleMailMessage mail=new SimpleMailMessage();
        mail.setTo(patient.getEmail());
        mail.setFrom("tojan050@gmail.com");
        mail.setText("New appointment has been subscribed to you ! check your portal ! ");
        mail.setSubject("New appointment has been subscribed to you !");
        try {
            javaMailSender.send(mail);
        } catch (MailException e) {
            e.printStackTrace();
        }

    }
}
