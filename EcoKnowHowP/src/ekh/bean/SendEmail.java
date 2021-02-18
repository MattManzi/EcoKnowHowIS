package ekh.bean;

import java.util.Properties;
import java.util.Random;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendEmail {
	public String getRandom() {
		Random r = new Random();
		int n = r.nextInt(999999);

		return String.format("%06d", n);
	}

	public boolean sendEmail(ClienteBean bean) {
		boolean test = false;

		String toEmail = bean.getEmail();
		String fromEmail = "testprogetti167@gmail.com";
		String password = "Pro.963741";

		Properties pr = new Properties();
		pr.setProperty("mail.smtp.host", "smtp.gmail.com");
		pr.setProperty("mail.smtp.port", "587");
		pr.setProperty("mail.smtp.auth", "true");
		pr.setProperty("mail.smtp.starttls.enable", "true");

		Session session = Session.getDefaultInstance(pr, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(fromEmail, password);
			}
		});

		try {
			Message mess = new MimeMessage(session);

			mess.setFrom(new InternetAddress(fromEmail));
			mess.setRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));

			mess.setSubject("User Email Verification");
			mess.setText(
					"Registrazione riuscita. Usa il seguente codice per verificare il tuo account: " + bean.getCodSicurezza());

			Transport.send(mess);
			test = true;

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return test;
	}
}
