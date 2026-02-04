package es.jllopezalvarez.psp.ut05servicios.ejemplos.ejemplo03smtp;


import jakarta.mail.*;
import jakarta.mail.internet.*;

import javax.swing.*;
import java.util.Properties;

public class Ejemplo03ATexto {

    // Datos de conexión del servidor SMTP
    private static final String SMTP_HOST = "smtp.educa.madrid.org";
    private static final String SMTP_PORT = "587";

    public static void main(String[] args) {

        // Pedir credenciales con JOptionPane - Así no se guardan "hardcoded" en el repositorio de GitHub
        String usuario = JOptionPane.showInputDialog(null, "Introduce tu cuenta de EducaMadrid (sin @educa.madrid.org):");

        // Si se ha pulsado cancelar, o se cierra el diálogo, termina el programa.
        if (usuario == null) {
            JOptionPane.showMessageDialog(null, "Operación cancelada");
            return;
        }

        // Pedir contraseña
        JPasswordField passwordField = new JPasswordField();
        int opcion = JOptionPane.showConfirmDialog(null, passwordField, "Contraseña:", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        // Si no se ha pulsado "Aceptar / Ok", se termina el programa.
        if (opcion != JOptionPane.OK_OPTION) {
            JOptionPane.showMessageDialog(null, "Operación cancelada");
            return;
        }

        // Como el password se devuelve como un array de caracteres, se convierte a String
        String password = new String(passwordField.getPassword());

        // Pedir credenciales con JOptionPane - Así no se guardan "hardcoded" en el repositorio de GitHub
        String destinatario = JOptionPane.showInputDialog(null, "Introduce la dirección de correo del destinatario:");

        // Si se ha pulsado cancelar, o se cierra el diálogo, termina el programa.
        if (destinatario == null){
            JOptionPane.showMessageDialog(null, "Operación cancelada");
            return;
        }

        // Configurar propiedades SMTP para la sesión
        Properties props = new Properties();
        props.put("mail.smtp.host", SMTP_HOST);
        props.put("mail.smtp.port", SMTP_PORT);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        // Crear sesión. Al crearla se define el Authenticator como clase anónima
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(usuario, password);
            }
        });

        try {
            // Crear mensaje de texto plano.
            // Ojo, porque los servidores pueden rechazar o ignorar correos muy simples del tipo "Esto es una prueba".
            MimeMessage mensaje = new MimeMessage(session);
            mensaje.setFrom(new InternetAddress(usuario + "@educa.madrid.org"));
            mensaje.setRecipients(Message.RecipientType.TO, destinatario); // Se puede usar CC o BCC también.
            mensaje.setSubject("Correo de prueba desde Jakarta Mail");
            mensaje.setText("Hola, este es un correo de prueba enviado desde Java usando Jakarta Mail.");

            // Enviar mensaje
            Transport.send(mensaje);

            JOptionPane.showMessageDialog(null, "Correo enviado correctamente");

        } catch (MessagingException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al enviar correo: " + e.getMessage());
        }
    }
}
