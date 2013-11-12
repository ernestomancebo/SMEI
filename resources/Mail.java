package com.claro.metricat1.util;

import java.io.FileInputStream;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.log4j.Logger;

public class Mail {

	static final Logger log = Logger.getLogger(Mail.class.getName());

	/**
	 * Para enviar mails.
	 * 
	 * @param error
	 *            <code>true</code> si ocurrio un error, <code>false</code> si
	 *            todo marcho bien.
	 */
	public static void enviarMail(boolean error) {

		try {
			log.info("Iniciando la creación del Mail.");

			Properties mailProperties = new Properties();
			mailProperties.load(new FileInputStream(
					"resources/properties/mail.properties"));

			Properties sysProperties = System.getProperties();

			String[] receptores;

			if (error) {
				receptores = new String(mailProperties.getProperty("error"))
						.split("(\\s*,\\s*)");
			} else {
				receptores = new String(mailProperties.getProperty("to"))
						.split("(\\s*,\\s*)");
			}

			sysProperties.setProperty("mail.smtp.host",
					mailProperties.getProperty("host"));

			Session session = Session.getDefaultInstance(sysProperties);
			MimeMessage mensaje = new MimeMessage(session);
			BodyPart cuerpoMensaje = new MimeBodyPart();
			Multipart multiParteMsj = new MimeMultipart();

			DataSource archivoAdjunto;

			if (error) {
				archivoAdjunto = new FileDataSource(
						"resources/data/logs/MetricaT1Log.log");
			} else {
				log.info("Adjuntando archivo: reports/semanal/reporte T1 "
						+ Util.getBegginAndEndOfWeek() + ".xlsx");
				archivoAdjunto = new FileDataSource(
						"reports/semanal/reporte T1 "
								+ Util.getBegginAndEndOfWeek() + ".xlsx");
			}

			mensaje.setFrom(new InternetAddress(mailProperties
					.getProperty("from")));

			log.info("Agregando destinatarios: " + receptores);

			for (String to : receptores) {
				mensaje.addRecipient(Message.RecipientType.TO,
						new InternetAddress(to));
			}

			if (error) {
				mensaje.setSubject("Incidente en reporte Metrica T1");
				cuerpoMensaje
						.setContent(
								"<h4>Incidente en Reporte Metrica T1. Verificar log adjunto.</h4>",
								"text/html");
			} else {
				mensaje.setSubject("Reporte de Metrica T1 ");
				cuerpoMensaje.setContent(
						"<h4>Reporte de la semana "
								+ Util.getBegginAndEndOfWeek() + "</h4>",
						"text/html");
			}

			multiParteMsj.addBodyPart(cuerpoMensaje);

			cuerpoMensaje = new MimeBodyPart();
			cuerpoMensaje.setDataHandler(new DataHandler(archivoAdjunto));

			if (error) {
				cuerpoMensaje.setFileName("MetricaT1Log.log");
			} else {
				cuerpoMensaje.setFileName("reporte T1 "
						+ Util.getBegginAndEndOfWeek() + ".xlsx");
			}

			multiParteMsj.addBodyPart(cuerpoMensaje);
			mensaje.setContent(multiParteMsj);
			Transport.send(mensaje);

			log.info("Mensaje enviado.");
		} catch (Exception e) {
			log.error(e.toString());
			e.printStackTrace();
			// enviarMail(true);
		}
	}
}