package com.biblioteca.email;

import java.util.List;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import com.biblioteca.entity.AutorCybertesisDetalle;

@Component("email")
public class Email {
	private static final Log LOG = LogFactory.getLog(Email.class);

	private final Properties properties = new Properties();   
    private Session session;
    
    private void init() {

        properties.put("mail.smtp.host", "smtp.gmail.com"); //smtp.gmail.com  m.outlook.com
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.port", 25);//25
        properties.put("mail.smtp.mail.sender", "biblioucsm@gmail.com");
        properties.put("mail.smtp.user", "biblioucsm@gmail.com"); //quien envia
        properties.put("mail.smtp.auth", "true");

        session = Session.getDefaultInstance(properties);
        session.setDebug(false);
    }

    public void sendEmail() {
        init();
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress((String) properties.get("mail.smtp.mail.sender")));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress("chavez20066@gmail.com"));
            message.setSubject("Prueba");
            message.setText("Hola mundo esta es una prueba de mensaje");
            Transport t = session.getTransport("smtp");
            t.connect((String) properties.get("mail.smtp.user"), "Biblioteca");
            t.sendMessage(message, message.getAllRecipients());
            t.close();
        } catch (MessagingException me) {
            //Aqui se deberia o mostrar un mensaje de error o en lugar
            //de no hacer nada con la excepcion, lanzarla para que el modulo
            //superior la capture y avise al usuario con un popup, por ejemplo.
        }

    }

    public void sendEmailAdjunto(String RutaPDF, List<AutorCybertesisDetalle> list) {
        init();
        LOG.info("sendEmailAdjunto() correo: "+list.size());
        LOG.info("sendEmailAdjunto() correo: "+list.get(0).getCorreo());
        LOG.info("sendEmailAdjunto() ruta: "+RutaPDF);
        try {
            BodyPart texto = new MimeBodyPart();
            texto.setText("Constancia envíada por el Sistema de Bibliotecas UCSM");

            BodyPart adjunto = new MimeBodyPart();
            //adjunto.setDataHandler(new DataHandler(new FileDataSource("C:\\Users\\jbasurco\\Downloads\\71.0590.IS.pdf")));
            adjunto.setDataHandler(new DataHandler(new FileDataSource(RutaPDF)));

            adjunto.setFileName("constancia.pdf");

            MimeMultipart multiParte = new MimeMultipart();

            multiParte.addBodyPart(texto);
            multiParte.addBodyPart(adjunto);

            MimeMessage message = new MimeMessage(session);
            // Se rellena el From
            message.setFrom(new InternetAddress("biblioucsm@gmail.com"));

            // Se rellenan los destinatarios
           
            Address[] destinos = new Address[list.size()];//Aqui usamos el arreglo de destinatarios
            
            for (int i = 0; i < destinos.length; i++) {
                destinos[i] = new InternetAddress(list.get(i).getCorreo());
                LOG.info("sendEmailAdjunto() correo: "+list.get(i).getCorreo());
            }
            
            message.addRecipients(Message.RecipientType.TO, destinos);//agregamos los destinatarios
            // Se rellena el subject
            message.setSubject("Constancia Autorización de Publicación de Tesis");

            // Se mete el texto y la foto adjunta.
            message.setContent(multiParte);

            
            Transport t = session.getTransport("smtp");
            t.connect("biblioucsm@gmail.com", "Biblioteca");
            t.sendMessage(message, message.getAllRecipients());
            t.close();

        } catch (MessagingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
    
    

}
