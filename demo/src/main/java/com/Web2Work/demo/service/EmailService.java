package com.Web2Work.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String emailOrigen;

    // EMAIL DE VERIFICACIÓN DE CUENTA AL REGISTRARSE
    public void enviarEmailVerificacion(String emailDestino,
                                        String nombre,
                                        String token) {
        try {
            MimeMessage mensaje = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mensaje, true, "UTF-8");

            helper.setFrom(emailOrigen);
            helper.setTo(emailDestino);
            helper.setSubject("Web2Work - Verifica tu cuenta");

            String contenido = "<div style='font-family: Arial, sans-serif; "
                    + "max-width: 560px; margin: 0 auto;'>"
                    + "<div style='background-color: #1B2D3E; padding: 24px; "
                    + "text-align: center;'>"
                    + "<h1 style='color: #FFFFFF; font-size: 28px; margin: 0;'>"
                    + "web2<span style='color: #2A9D8F;'>work</span></h1>"
                    + "</div>"
                    + "<div style='padding: 32px; background-color: #FFFFFF; "
                    + "border: 1px solid #e0e0e0;'>"
                    + "<h2 style='color: #1B2D3E;'>Hola, " + nombre + "</h2>"
                    + "<p style='color: #333333;'>Gracias por registrarte en Web2Work. "
                    + "Para activar tu cuenta haz clic en el botón:</p>"
                    + "<div style='text-align: center; margin: 32px 0;'>"
                    + "<a href='http://localhost:8080/verificar?token=" + token + "' "
                    + "style='background-color: #1B2D3E; color: #FFFFFF; "
                    + "padding: 12px 24px; text-decoration: none; "
                    + "border-radius: 4px; font-size: 15px;'>"
                    + "ACTIVAR CUENTA</a>"
                    + "</div>"
                    + "<p style='color: #777777; font-size: 13px;'>"
                    + "Si no has creado esta cuenta puedes ignorar este correo.</p>"
                    + "<p style='color: #777777; font-size: 13px;'>"
                    + "El enlace expirará en 24 horas.</p>"
                    + "</div>"
                    + "<div style='background-color: #F5F5F5; padding: 16px; "
                    + "text-align: center;'>"
                    + "<p style='color: #777777; font-size: 12px; margin: 0;'>"
                    + "Web2Work - Gestión de formación en empresa</p>"
                    + "</div>"
                    + "</div>";

            helper.setText(contenido, true);
            mailSender.send(mensaje);

        } catch (MessagingException e) {
            System.err.println("Error al enviar email de verificación: "
                    + e.getMessage());
        }
    }

    // EMAIL DE RECUPERACIÓN DE CONTRASEÑA
    public void enviarEmailRecuperacion(String emailDestino,
                                        String nombre,
                                        String token) {
        try {
            MimeMessage mensaje = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mensaje, true, "UTF-8");

            helper.setFrom(emailOrigen);
            helper.setTo(emailDestino);
            helper.setSubject("Web2Work - Recuperación de contraseña");

            String contenido = "<div style='font-family: Arial, sans-serif; "
                    + "max-width: 560px; margin: 0 auto;'>"
                    + "<div style='background-color: #1B2D3E; padding: 24px; "
                    + "text-align: center;'>"
                    + "<h1 style='color: #FFFFFF; font-size: 28px; margin: 0;'>"
                    + "web2<span style='color: #2A9D8F;'>work</span></h1>"
                    + "</div>"
                    + "<div style='padding: 32px; background-color: #FFFFFF; "
                    + "border: 1px solid #e0e0e0;'>"
                    + "<h2 style='color: #1B2D3E;'>Hola, " + nombre + "</h2>"
                    + "<p style='color: #333333;'>Hemos recibido una solicitud para "
                    + "restablecer la contraseña de tu cuenta. "
                    + "Haz clic en el botón para continuar:</p>"
                    + "<div style='text-align: center; margin: 32px 0;'>"
                    + "<a href='http://localhost:8080/recuperar/nueva-password?token="
                    + token + "' "
                    + "style='background-color: #2A9D8F; color: #FFFFFF; "
                    + "padding: 12px 24px; text-decoration: none; "
                    + "border-radius: 4px; font-size: 15px;'>"
                    + "RESTABLECER CONTRASEÑA</a>"
                    + "</div>"
                    + "<p style='color: #777777; font-size: 13px;'>"
                    + "Si no has solicitado este cambio puedes ignorar este correo. "
                    + "Tu contraseña no será modificada.</p>"
                    + "<p style='color: #777777; font-size: 13px;'>"
                    + "El enlace expirará en 24 horas.</p>"
                    + "</div>"
                    + "<div style='background-color: #F5F5F5; padding: 16px; "
                    + "text-align: center;'>"
                    + "<p style='color: #777777; font-size: 12px; margin: 0;'>"
                    + "Web2Work - Gestión de formación en empresa</p>"
                    + "</div>"
                    + "</div>";

            helper.setText(contenido, true);
            mailSender.send(mensaje);

        } catch (MessagingException e) {
            System.err.println("Error al enviar email de recuperación: "
                    + e.getMessage());
        }
    }

    // EMAIL DE BIENVENIDA TRAS VERIFICAR
    public void enviarEmailBienvenida(String emailDestino, String nombre) {
        try {
            MimeMessage mensaje = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mensaje, true, "UTF-8");

            helper.setFrom(emailOrigen);
            helper.setTo(emailDestino);
            helper.setSubject("Web2Work - Bienvenido/a a la plataforma");

            String contenido = "<div style='font-family: Arial, sans-serif; "
                    + "max-width: 560px; margin: 0 auto;'>"
                    + "<div style='background-color: #1B2D3E; padding: 24px; "
                    + "text-align: center;'>"
                    + "<h1 style='color: #FFFFFF; font-size: 28px; margin: 0;'>"
                    + "web2<span style='color: #2A9D8F;'>work</span></h1>"
                    + "</div>"
                    + "<div style='padding: 32px; background-color: #FFFFFF; "
                    + "border: 1px solid #e0e0e0;'>"
                    + "<h2 style='color: #1B2D3E;'>¡Bienvenido/a, " + nombre + "!</h2>"
                    + "<p style='color: #333333;'>Tu cuenta ha sido activada "
                    + "correctamente. Ya puedes acceder a la plataforma Web2Work.</p>"
                    + "<div style='text-align: center; margin: 32px 0;'>"
                    + "<a href='http://localhost:8080/login' "
                    + "style='background-color: #1B2D3E; color: #FFFFFF; "
                    + "padding: 12px 24px; text-decoration: none; "
                    + "border-radius: 4px; font-size: 15px;'>"
                    + "INICIAR SESIÓN</a>"
                    + "</div>"
                    + "</div>"
                    + "<div style='background-color: #F5F5F5; padding: 16px; "
                    + "text-align: center;'>"
                    + "<p style='color: #777777; font-size: 12px; margin: 0;'>"
                    + "Web2Work - Gestión de formación en empresa</p>"
                    + "</div>"
                    + "</div>";

            helper.setText(contenido, true);
            mailSender.send(mensaje);

        } catch (MessagingException e) {
            System.err.println("Error al enviar email de bienvenida: "
                    + e.getMessage());
        }
    }
}