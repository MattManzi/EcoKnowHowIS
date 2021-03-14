package ekh.support;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import ekh.bean.AmministratoreBean;
import ekh.bean.ClienteBean;

public class SendEmail {
	public String getRandom() {
		GeneraRandom gr = new GeneraRandom();
		return gr.getRandom();
	}

	public boolean sendEmail(ClienteBean bean) {
		boolean test = false;

		String toEmail = bean.getEmail();
		String fromEmail = "testprogetti167@gmail.com";
		String password = "Pro.963741";

		try {
			if (!bean.getEmail().equals(fromEmail)) {
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
							"Benvenuto. Per completare la registrazione inserire il codice: " + bean.getCodSicurezza());

					Transport.send(mess);
					test = true;

				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			} else
				throw new Exception("ERRORE-SendEmail: Email non valida.");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return test;
	}

	public boolean recuperaPassword(ClienteBean client, AmministratoreBean admin) {
		boolean test = false;
		String toEmail = "";
		String codSicurezza = "";

		if (client != null) {
			toEmail = client.getEmail();
			codSicurezza = client.getCodSicurezza();
		} else {
			toEmail = admin.getEmail();
			codSicurezza = admin.getCodSicurezza();
		}

		String fromEmail = "testprogetti167@gmail.com";
		String password = "Pro.963741";

		try {
			if (!toEmail.equals(fromEmail)) {
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

					mess.setSubject("Reimpostazione password");
					mess.setText("Benvenuto. Inserire il seguente codice per reimpostare la password  " + codSicurezza);

					Transport.send(mess);
					test = true;

				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			} else
				throw new Exception("ERRORE-SendEmail: Email non valida.");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return test;
	}

	public boolean confermaPiano(ClienteBean client) {
		boolean test = false;

		String toEmail = client.getEmail();
		String fromEmail = "testprogetti167@gmail.com";
		String password = "Pro.963741";

		try {
			if (!client.getEmail().equals(fromEmail)) {
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

					mess.setSubject("Piano");
					mess.setText(
							"il tuo piano è stato creato con successo.\nPer controllare il suo stato accedi al tuo storico ordini.\nPotrai in oltre aggiungere al piano eventuali file .pdf come note sul rapporto di prova e/o le Schede Dati di Sicurezza.");

					Transport.send(mess);
					test = true;

				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			} else
				throw new Exception("ERRORE-SendEmail: Email non valida.");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return test;
	}
}
