package pilotage.service.mail;

import java.text.MessageFormat;
import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;
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
import javax.naming.Context;
import javax.naming.InitialContext;

import org.apache.log4j.Logger;

import pilotage.service.constants.PilotageConstants;

public class MailService {

	/**
	 * Envoi d'un mail
	 * @param receiver
	 * @param subjectType
	 * @param content
	 * @param images TODO
	 * @return
	 */
	public static boolean sendEmail(String receiver, String subjectType, String content, String[] param, Map<String, String> images) {
		return sendEmail(new String[]{receiver}, subjectType, content, param, images);
	}
	
	/**
	 * envoi d'un mail à toute la liste passée en paramètre
	 * @param receivers
	 * @param subjectType
	 * @param content
	 * @param images TODO
	 * @return
	 */
	public static boolean sendEmail(String[] receivers, String subjectType, String content, String[] param, Map<String, String> images) {
		String serverHost = null;
		String serverPort = null;
		String senderLogin = null;
		String senderPassword = null;
		String subject = null;
		String contentText = null;
		String fromAdress = null;
		String destinataires = "";
		String authentification = null;

		try {
			//récupération des infos du serveur de mail
			Context ctx = new InitialContext();
			String path = ctx.lookup(PilotageConstants.MAILSERVER_CONFIG_PATH).toString();
			
			DocumentProperties dp = new DocumentProperties(path);
			serverHost 		= dp.getValue(PilotageConstants.MAILSERVER_HOST);
			serverPort		= dp.getValue(PilotageConstants.MAILSERVER_PORT);
			authentification= dp.getValue(PilotageConstants.MAILSERVER_AUTHENTIFICATION);
			senderLogin 	= dp.getValue(PilotageConstants.MAILSERVER_SENDER_LOGIN);
			senderPassword	= dp.getValue(PilotageConstants.MAILSERVER_SENDER_PASSWORD);
			fromAdress		= dp.getValue(PilotageConstants.MAILSERVER_SENDER_FROM);
			subject 		= dp.getValue(subjectType);
			contentText		= dp.getValue(content);
			if(subject == null || subject.equals(""))
				subject = subjectType;
			if(contentText == null || contentText.equals(""))
				contentText = content;
			if(param != null)
				contentText = MessageFormat.format(contentText, (Object[])param);

			//creation du mail session
			Properties pro = new Properties();
			pro.setProperty("mail.smtp.host", serverHost);
			pro.setProperty("mail.smtp.port", serverPort);
			pro.setProperty("mail.smtp.user", senderLogin);
			pro.setProperty("mail.smtp.password", senderPassword);
			pro.setProperty("mail.smtp.auth", authentification);
			
			// parametre pour un compte gmail
			// pro.setProperty("mail.smtp.starttls.enable","true");
			
			/*pro.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory"); 
            pro.setProperty("mail.smtp.socketFactory.fallback", "false");
            pro.setProperty("mail.smtp.socketFactory.port", "465"); */
	
			Session mailSession = null;
			mailSession = Session.getInstance(pro, new SMTPAuthenticator(senderLogin, senderPassword, "true".equals(authentification)));
			Transport transport =  mailSession.getTransport("smtp");
	
			//creation du message
			MimeMessage message = new MimeMessage(mailSession);
			
			InternetAddress fromAddr = null;
			fromAddr = new InternetAddress(fromAdress);
			message.setFrom(fromAddr);
	
			InternetAddress[] toAddresses = new InternetAddress[receivers.length];
			for(int i = 0; i < receivers.length; ++i){
				toAddresses[i] = new InternetAddress(receivers[i]);
				destinataires = destinataires + receivers[i] +";";
			}
			message.setRecipients(Message.RecipientType.TO, toAddresses);
	
			message.setSubject(subject);     			  //set the subject
			message.setSentDate(new Date());              //set the date
			
			 Multipart multipart = new MimeMultipart();
			 
			//Message HTML
			BodyPart bodyPart = new MimeBodyPart();
	        bodyPart.setContent(contentText,"text/html; charset=UTF-8"); 
	        multipart.addBodyPart(bodyPart);
	        
	        //images attachées
	        if(images != null && !images.isEmpty()){
	        	for(Entry<String, String> element : images.entrySet()){
	        		MimeBodyPart imagePart = new MimeBodyPart();
	        		DataSource fds = new FileDataSource(element.getValue());
	        		imagePart.setDataHandler(new DataHandler(fds));
	        		imagePart.setHeader("Content-ID", element.getKey());
	        		multipart.addBodyPart(imagePart);
	        	}
	        }
	       
	        message.setContent(multipart);
			message.saveChanges();

			transport.connect();
			transport.sendMessage(message, message.getAllRecipients());   //send email
			transport.close();
			Logger.getLogger("pilotage").info("Envoi de mail OK - " +  subject + " - " + destinataires);
			return true;
		}
		catch (Exception e) {
			Logger.getLogger("erreur").error("Envoi de mail - KO", e);
			return false;
		}
	}
	
	/**
	 * envoi d'un mail à toute la liste passée en paramètre
	 * @param receivers
	 * @param subjectType
	 * @param content
	 * @param images TODO
	 * @return
	 */
	public static boolean sendEmailGup(String[] receivers, String[] receiversCC, String[] receiversCCI, String subjectType, String content, String[] param, Map<String, String> images) {
		String serverHost = null;
		String serverPort = null;
		String senderLogin = null;
		String senderPassword = null;
		String subject = null;
		String contentText = null;
		String fromAdress = null;
		String destinataires = "";
		String authentification = null;

		try {
			//récupération des infos du serveur de mail
			Context ctx = new InitialContext();
			String path = ctx.lookup(PilotageConstants.MAILSERVER_CONFIG_PATH).toString();
			
			DocumentProperties dp = new DocumentProperties(path);
			serverHost 		= dp.getValue(PilotageConstants.MAILSERVER_HOST);
			serverPort		= dp.getValue(PilotageConstants.MAILSERVER_PORT);
			authentification= dp.getValue(PilotageConstants.MAILSERVER_AUTHENTIFICATION);
			senderLogin 	= dp.getValue(PilotageConstants.MAILSERVER_SENDER_LOGIN);
			senderPassword	= dp.getValue(PilotageConstants.MAILSERVER_SENDER_PASSWORD);
			fromAdress		= dp.getValue(PilotageConstants.MAILSERVER_SENDER_FROM_GUP);
			subject 		= dp.getValue(subjectType);
			contentText		= dp.getValue(content);
			if(subject == null || subject.equals(""))
				subject = subjectType;
			if(contentText == null || contentText.equals(""))
				contentText = content;
			if(param != null)
				contentText = MessageFormat.format(contentText, (Object[])param);

			//creation du mail session
			Properties pro = new Properties();
			pro.setProperty("mail.smtp.host", serverHost);
			pro.setProperty("mail.smtp.port", serverPort);
			pro.setProperty("mail.smtp.user", senderLogin);
			pro.setProperty("mail.smtp.password", senderPassword);
			pro.setProperty("mail.smtp.auth", authentification);
			
			// parametre pour un compte gmail
			// pro.setProperty("mail.smtp.starttls.enable","true");
			
			/*pro.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory"); 
            pro.setProperty("mail.smtp.socketFactory.fallback", "false");
            pro.setProperty("mail.smtp.socketFactory.port", "465"); */
	
			Session mailSession = null;
			mailSession = Session.getInstance(pro, new SMTPAuthenticator(senderLogin, senderPassword, "true".equals(authentification)));
			Transport transport =  mailSession.getTransport("smtp");
	
			//creation du message
			MimeMessage message = new MimeMessage(mailSession);
			
			InternetAddress fromAddr = null;
			fromAddr = new InternetAddress(fromAdress);
			message.setFrom(fromAddr);
			
			if(receiversCC != null) {
				InternetAddress[] toAddresses = new InternetAddress[receivers.length];
				for(int i = 0; i < receivers.length; ++i){
					toAddresses[i] = new InternetAddress(receivers[i]);
					destinataires = destinataires + receivers[i] +";";
				}
				message.setRecipients(Message.RecipientType.TO, toAddresses);
			}
			
			if(receiversCC != null) {
				InternetAddress[] toAddressesCC = new InternetAddress[receiversCC.length];
				for(int i = 0; i < receiversCC.length; ++i){
					toAddressesCC[i] = new InternetAddress(receiversCC[i]);
					destinataires = destinataires + receiversCC[i] +";";
				}
				message.setRecipients(Message.RecipientType.CC, toAddressesCC);
			}
			
			if(receiversCCI != null) {
				InternetAddress[] toAddressesCCI = new InternetAddress[receiversCCI.length];
				for(int i = 0; i < receiversCCI.length; ++i){
					toAddressesCCI[i] = new InternetAddress(receiversCCI[i]);
					destinataires = destinataires + receiversCCI[i] +";";
				}
				message.setRecipients(Message.RecipientType.BCC, toAddressesCCI);
			}
			
			message.setSubject(subject);     			  //set the subject
			message.setSentDate(new Date());              //set the date
			
			 Multipart multipart = new MimeMultipart();
			 
			//Message HTML
			BodyPart bodyPart = new MimeBodyPart();
	        bodyPart.setContent(contentText,"text/html; charset=UTF-8"); 
	        multipart.addBodyPart(bodyPart);
	        
	        //images attachées
	        if(images != null && !images.isEmpty()){
	        	for(Entry<String, String> element : images.entrySet()){
	        		MimeBodyPart imagePart = new MimeBodyPart();
	        		DataSource fds = new FileDataSource(element.getValue());
	        		imagePart.setDataHandler(new DataHandler(fds));
	        		imagePart.setHeader("Content-ID", element.getKey());
	        		multipart.addBodyPart(imagePart);
	        	}
	        }
	       
	        message.setContent(multipart);
			message.saveChanges();

			transport.connect();
			transport.sendMessage(message, message.getAllRecipients());   //send email
			transport.close();
			Logger.getLogger("pilotage").info("Envoi de mail OK - " +  subject + " - " + destinataires);
			return true;
		}
		catch (Exception e) {
			Logger.getLogger("erreur").error("Envoi de mail - KO", e);
			return false;
		}
	}
	
	/**
	 * envoi d'un mail à toute la liste passée en paramètre
	 * @param receivers
	 * @param subjectType
	 * @param content
	 * @param images TODO
	 * @return
	 */
	public static boolean sendEmailPieceJointe(String[] receivers, String subjectType, String content, String[] param, Map<String, String> images, String cheminFichier) {
		String serverHost = null;
		String serverPort = null;
		String senderLogin = null;
		String senderPassword = null;
		String subject = null;
		String contentText = null;
		String fromAdress = null;
		String authentification = null;

		try {
			//récupération des infos du serveur de mail
			Context ctx = new InitialContext();
			String path = ctx.lookup(PilotageConstants.MAILSERVER_CONFIG_PATH).toString();
			
			DocumentProperties dp = new DocumentProperties(path);
			serverHost 		= dp.getValue(PilotageConstants.MAILSERVER_HOST);
			serverPort		= dp.getValue(PilotageConstants.MAILSERVER_PORT);
			authentification= dp.getValue(PilotageConstants.MAILSERVER_AUTHENTIFICATION);
			senderLogin 	= dp.getValue(PilotageConstants.MAILSERVER_SENDER_LOGIN);
			senderPassword	= dp.getValue(PilotageConstants.MAILSERVER_SENDER_PASSWORD);
			fromAdress		= dp.getValue(PilotageConstants.MAILSERVER_SENDER_FROM);
			subject 		= dp.getValue(subjectType);
			contentText		= dp.getValue(content);
			
			/*serverHost = "126.248.101.60";
			fromAdress = "melanie.poggi@it-ce.fr";*/
			
			if(subject == null || subject.equals(""))
				subject = subjectType;
			if(contentText == null || contentText.equals(""))
				contentText = content;
			if(param != null)
				
				contentText = MessageFormat.format(contentText, (Object[])param);

			//creation du mail session
			Properties pro = new Properties();
			pro.setProperty("mail.smtp.host", serverHost);
			pro.setProperty("mail.smtp.port", serverPort);
			pro.setProperty("mail.smtp.user", senderLogin);
			pro.setProperty("mail.smtp.password", senderPassword);
			pro.setProperty("mail.smtp.auth", authentification);
	
			Session mailSession = null;
			mailSession = Session.getInstance(pro, new SMTPAuthenticator(senderLogin, senderPassword, "true".equals(authentification)));
			Transport transport =  mailSession.getTransport("smtp");
	
			//creation du message
			MimeMessage message = new MimeMessage(mailSession);
			
			InternetAddress fromAddr = null;
			fromAddr = new InternetAddress(fromAdress);
			message.setFrom(fromAddr);
			
			InternetAddress[] toAddresses = new InternetAddress[receivers.length];
			for(int i = 0; i < receivers.length; ++i){
				toAddresses[i] = new InternetAddress(receivers[i]);
			}
			message.setRecipients(Message.RecipientType.TO, toAddresses);
	
			message.setSubject(subject);     			  //set the subject
			message.setSentDate(new Date());              //set the date
			
			Multipart multipart = new MimeMultipart();
			 
			//Message HTML
			BodyPart bodyPart = new MimeBodyPart();
	        bodyPart.setContent(contentText,"text/html; charset=UTF-8"); 
	        multipart.addBodyPart(bodyPart);
	        
	      //creation et ajout de la pièce jointe
	        MimeBodyPart piecePart = new MimeBodyPart();
			//DataSource source = new FileDataSource(cheminFichier);
			
	        piecePart.attachFile(cheminFichier);
			multipart.addBodyPart(piecePart);
	        
	        //images attachées
	        /*if(images != null && !images.isEmpty()){
	        	for(Entry<String, String> element : images.entrySet()){
	        		System.out.println(element.getValue());
	        		//MimeBodyPart imagePart = new MimeBodyPart();
	        		//DataSource fds = new FileDataSource(element.getValue());
	        		//imagePart.setDataHandler(new DataHandler(fds));
	        		//imagePart.setHeader("Content-ID", element.getKey());
	        		//multipart.addBodyPart(imagePart);
	        	}
	        }*/
	       
	        message.setContent(multipart);
			message.saveChanges();

			transport.connect();
			transport.sendMessage(message, message.getAllRecipients());   //send email
			transport.close();

			return true;
		}
		catch (Exception e) {
			Logger.getLogger("erreur").error("Envoi de mail - ", e);
			return false;
		}
	}
}
