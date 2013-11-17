/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package smei.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import smei.dao.DBConnection;
import smei.modelos.Usuario;

/**
 *
 * @author Ernesto
 */
public class Mail {

    private static Mail instancia = new Mail();
    private Connection conn;
    private PreparedStatement pstm;
    private ResultSet rs;
    private String smtp_user;
    private String smtp_password;

    public enum TipoEmail {

        CREAR_USUARIO("CREAR_USUARIO"),
        MODIFICACION_USUARIO("MODIFICACION_USUARIO"),
        ACTUALIZACION_USUARIO("ACTUALIZACION_USUARIO");
        private String tipoEmail;

        private TipoEmail(String s) {
            tipoEmail = s;
        }

        public String getTipoEmail() {
            return tipoEmail;
        }
    }

    private Mail() {
        try {
            Properties mailProperties = new Properties();
            mailProperties.load(new FileInputStream("mail.properties"));

            smtp_user = mailProperties.getProperty("user");
            smtp_password = Security.decrypt(mailProperties.getProperty("password"));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Mail.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Mail.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static Mail getInstance() {
        return instancia;
    }

    public void sendAMail(TipoEmail tipo, Usuario usuario, String descripcion) {
        ArrayList<String> copia = new ArrayList<String>();
        String titulo = new String();
        String contenido = new String();
        Properties props = new Properties();
        conn = DBConnection.getConnection();

        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(smtp_user, smtp_password);
            }
        });


        try {
            //Obteniendo el titulo y el contenido del mensaje
            pstm = conn.prepareCall("select coalesce(titulo_personalizado, titulo_por_defecto) as titulo, "
                    + "coalesce(contenido_personalizado, contenido_por_defecto) as contenido from notificaciones "
                    + "where nombre = ? and habilitada = true");
            pstm.setString(1, tipo.getTipoEmail());

            rs = pstm.executeQuery();
            if (rs.next()) {
                titulo = rs.getString("titulo");
                contenido = rs.getString("contenido");
            } else {
                return;
            }

            contenido = contenido.replaceAll(Pattern.quote("\\n"), "\n\n");
            contenido = contenido.replaceAll(Pattern.quote("@@usuario"), usuario.getDescripcion());
            contenido = contenido.replaceAll(Pattern.quote("@@detalle"), descripcion);

            System.out.println(contenido);

            //Agregando correos de copia
            pstm = conn.prepareStatement("select u.email from usuario u, rol r where u.idRol = r.idRol "
                    + "and r.idRol = (select idRol from rol n where upper(n.nombre) = 'ADMINISTRADOR')");
            rs = pstm.executeQuery();

            while (rs.next()) {
                copia.add(rs.getString("email"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Mail.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("SMEI@gmail.com"));

            for (String s : copia) {
                message.setRecipients(Message.RecipientType.CC,
                        InternetAddress.parse(s));
            }

            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse("scalator.5@hotmail.com"));
            message.setSubject(titulo);
            message.setText(contenido);
            Transport.send(message);

            System.out.println("Done");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }
}
