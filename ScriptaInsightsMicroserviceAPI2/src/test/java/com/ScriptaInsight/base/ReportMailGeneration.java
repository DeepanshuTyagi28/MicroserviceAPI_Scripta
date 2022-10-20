package com.ScriptaInsight.base;

import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.testng.annotations.Test;

public class ReportMailGeneration {

	public static void main(String[] args) {
		String to = com.ScriptaInsight.base.TestBase.MailTO(args);
		String CC = com.ScriptaInsight.base.TestBase.Mail_CC(args);
		String from = com.ScriptaInsight.base.TestBase.MailFrom(args);
		final String username = com.ScriptaInsight.base.TestBase.EmailEncryptedUsername(args);
		final String password = com.ScriptaInsight.base.TestBase.EmailEncryptedPassword(args);

		String host = "smtp.gmail.com";
		Properties prop = new Properties();
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.port", "587");
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.starttls.enable", "true");

		Session session = Session.getInstance(prop, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
			message.addRecipients(Message.RecipientType.CC, InternetAddress.parse(CC));
			message.setSubject("Scripta MicroService API Report");
			BodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setText("Hi All ," + "\r\n" + "\r\n" + "Please find below the attached Microservice Report."
					+ "\r\n" + "\r\n" + "Thanks And Regards" + "\r\n" + "Deepanshu Tyagi");
			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBodyPart);
			messageBodyPart = new MimeBodyPart();
			String filename = "C:\\Users\\Deepanshu Tyagi\\git\\QA_Atomation\\ScriptaMicroserviceAPI\\test-output\\emailable-report.html";
			DataSource source = new FileDataSource(filename);
			messageBodyPart.setDataHandler(new DataHandler(source));
			messageBodyPart.setFileName(filename);
			multipart.addBodyPart(messageBodyPart);
			multipart.addBodyPart(messageBodyPart);

			message.setContent(multipart);

			Transport.send(message);
			System.out.println("Scripta Microservice API Report Generated and shared over Email");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
}
