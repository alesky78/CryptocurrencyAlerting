package it.spaghettisource.cryptocurrencyalerting.action;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import it.spaghettisource.cryptocurrencyalerting.repository.CommonEntity;

/**
 * MailAction
 * 
 * 
 * @author Alessandro D'Ottavio
 * @version 1.0
 */
public class MailAction extends CommonEntity implements Action {

	
	public EncryptType encryptType; 
	
	public String username;
	public String password;
	public String host;
	public String port;	
	public String authentication;
	
	//message data
	public String subject;
	

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getAuthentication() {
		return authentication;
	}

	public void setAuthentication(String authentication) {
		this.authentication = authentication;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	public EncryptType getEncryptType() {
		return encryptType;
	}

	public void setEncryptType(EncryptType encryptType) {
		this.encryptType = encryptType;
	}
	

	@Override
	public void trigger(String message) {

		Session session = null;
		Properties prop = new Properties();
		
		//server info
		prop.put("mail.smtp.host", host);
		prop.put("mail.smtp.port", port);
		
		//Encrypt logic
		if(encryptType==EncryptType.TLS) {
			prop.put("mail.smtp.starttls.enable", "true");
		}else if (encryptType==EncryptType.SSL) {
			prop.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");			
		}
		
		//authentication
		if("true".equals(authentication)) {
			prop.put("mail.smtp.auth", authentication);
			session = Session.getInstance(prop, 
					new javax.mail.Authenticator() {
									protected PasswordAuthentication getPasswordAuthentication() {
											return new PasswordAuthentication(username, password);
									}
					});			
		}else {
			session = Session.getInstance(prop);
		}
		
		//send the message
		try {

			Message mailMessage = new MimeMessage(session);
			mailMessage.setFrom(new InternetAddress(username));
			mailMessage.setRecipients(Message.RecipientType.TO,InternetAddress.parse(username));
			mailMessage.setSubject(subject);
			mailMessage.setText(message);

			Transport.send(mailMessage);

		} catch (MessagingException e) {
			e.printStackTrace();
		}		 

	}
	
	
	public enum  EncryptType{
		
		TLS, SSL, NONE; 
		
	}
	

}
