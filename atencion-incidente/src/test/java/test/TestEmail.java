package test;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import pe.upc.atencionincidente.dao.SolicitudDAO;
import pe.upc.atencionincidente.form.DatosSolicitudForm;
import pe.upc.atencionincidente.model.Solicitante;
import pe.upc.atencionincidente.model.Solicitud;
import pe.upc.atencionincidente.service.EmailService;

public class TestEmail {

	
		
		
		public static void main(String args[]) {
	 
			// Spring Bean file you specified in /src/main/resources folder
			 ApplicationContext context = new ClassPathXmlApplicationContext("test-email.xml");
			 
	 
			// @Service("crunchifyEmail") <-- same annotation you specified in CrunchifyEmailAPI.java
			EmailService emailAPI = (EmailService) context.getBean("emailService");
			String toAddr = "alan@isend.pe";
			String fromAddr = "atencion@atencion.com";
	 
			// email subject
			String subject = "Hey.. This email is a notification";
	 
			// email body
			String body = "There you go.. You got an email.";
			emailAPI.readyToSendEmail(toAddr, fromAddr, subject, body);
		}
		  

	

}


