/* Author: Teo Kok Hien
 * Last Edit: 4 November 2013
 * 
 * This class attaches the Ministerial PDF Report into the email before sending it to the PMO
 */

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
 
public class EmailManager {
 
	public static int sendEmail() {
 
		final String username = "teamsafeguard@gmail.com";
		final String password = "teamsafeguard123";
 
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
 
		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});
 
		try {
 
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("from-email@gmail.com"));
			message.setRecipients(Message.RecipientType.TO,	InternetAddress.parse("kokyhien@gmail.com"));
			message.setSubject("SafeGuard Half-Hourly Ministerial Report");
			//message.setText("Dear Mail Crawler," + "\n\n No spam to my email, please!");

			//Create Message Content
			BodyPart msgText = new MimeBodyPart();
			msgText.setText("Dear Minister Office, \n\nPlease find the attached half hourly ministerial report. \n\n\n--------------------------------------------------------------------------- \nThis is an auto generated email, please do not reply to it.");
			
			//Create PDF Ministerial Report
			DataSource source = new FileDataSource("C:/Users/Owner/Desktop/MinisterialReport.pdf");
			MimeBodyPart pdfFile = new MimeBodyPart();
			pdfFile.setDataHandler(new DataHandler(source));
			pdfFile.setFileName("MinisterialReport.pdf");
			Multipart multipart = new MimeMultipart();
			
			
			//Attach Message and PDF Report
			multipart.addBodyPart(msgText);
			multipart.addBodyPart(pdfFile);
			message.setContent(multipart);
 
			Transport.send(message);
 
			System.out.println("Email Sent");
			
			return 1;
 
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
}