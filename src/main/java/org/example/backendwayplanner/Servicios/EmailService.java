package org.example.backendwayplanner.Servicios;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    private final JavaMailSender mailSender;

    // Inyección de dependencia del JavaMailSender
    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    // Envía un correo electrónico con un código de verificación en formato HTML
    public void envEmail(String para, String codigo) {
        MimeMessage mensaje = mailSender.createMimeMessage();

        try {
            // Ayuda a construir el mensaje con soporte para HTML
            MimeMessageHelper helper = new MimeMessageHelper(mensaje, true, "UTF-8");
            helper.setTo(para); // Destinatario
            helper.setSubject("¡Bienvenido a WayPlanner! Verifica tu cuenta"); // Asunto

            // Contenido HTML del correo con diseño visual y el código incrustado
            String contenidoHtml = """
                <html>
                <body style="font-family: Arial, sans-serif; background-color: #f4f4f4; padding: 20px;">
                    <div style="max-width: 600px; margin: auto; background: white; border-radius: 10px; padding: 30px; box-shadow: 0 2px 8px rgba(0,0,0,0.1);">
                        <h2 style="color: #2d89ef;">¡Hola viajero! 🧳✈️</h2>
                        <p>Gracias por unirte a <strong>WayPlanner</strong>, tu nueva herramienta para organizar tus viajes.</p>
                        <p>Para empezar, verifica tu correo electrónico con el siguiente código:</p>
                        <div style="text-align: center; margin: 30px 0;">
                            <span style="display: inline-block; background: #2d89ef; color: white; padding: 15px 25px; font-size: 28px; letter-spacing: 4px; border-radius: 8px;">
                                %s
                            </span>
                        </div>
                        <p>Si no solicitaste este correo, simplemente ignóralo.</p>
                        <p style="margin-top: 30px;">¡Feliz planificación! 🌍</p>
                        <p>— El equipo de WayPlanner</p>
                    </div>
                </body>
                </html>
                """.formatted(codigo);

            helper.setText(contenidoHtml, true); // 'true' indica que el contenido es HTML
            mailSender.send(mensaje); // Envía el correo
        } catch (MessagingException e) {
            e.printStackTrace(); // Manejo básico de error
        }
    }
}
