package org.example.backendwayplanner.Servicios;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void envEmail (String para, String codigo) {
        SimpleMailMessage mensaje = new SimpleMailMessage();
        mensaje.setTo(para);
        mensaje.setSubject("Verifica tu cuenta");
        mensaje.setText("Tu codigo de verificacion es: " + codigo);
        mailSender.send(mensaje);
    }
}
