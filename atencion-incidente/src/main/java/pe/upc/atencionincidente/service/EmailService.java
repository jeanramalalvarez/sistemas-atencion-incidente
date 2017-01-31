package pe.upc.atencionincidente.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
	 

	 
	@Service("emailService")
	public class EmailService {
	 
		@Autowired
		private MailSender senderMail; // MailSender interface defines a strategy
											// for sending simple mails
	 
		public void readyToSendEmail(String toAddress, String fromAddress, String subject, String msgBody) {
	 
			SimpleMailMessage crunchifyMsg = new SimpleMailMessage();
			crunchifyMsg.setFrom(fromAddress);
			crunchifyMsg.setTo(toAddress);
			crunchifyMsg.setSubject(subject);
			crunchifyMsg.setText(msgBody);
			senderMail.send(crunchifyMsg);
		}
	}