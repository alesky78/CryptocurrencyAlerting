package it.spaghettisource.cryptocurrencyalerting.action;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.spaghettisource.cryptocurrencyalerting.exception.ExceptionFactory;
import it.spaghettisource.cryptocurrencyalerting.utils.FileUtil;

/**
 * MailAction
 * 
 * 
 * @author Alessandro D'Ottavio
 * @version 1.0
 */
public class SmtpMailAction extends AbstractAction {

	static Logger log = LoggerFactory.getLogger(SmtpMailAction.class);
	
	public static String CONFIG_FILE_PATH = System.getProperty("user.dir")+ System.getProperty("file.separator")+"configuration/action/";
	public static String CONFIG_FILE_NAME = "mail.properties";


	private Properties prop;

	public SmtpMailAction(ExceptionFactory exceptionFactory) {
		super();
		init(exceptionFactory, CONFIG_FILE_PATH, CONFIG_FILE_NAME);

	}

	public SmtpMailAction(ExceptionFactory exceptionFactory,String configFilePath,String configFileName) {
		super();
		init(exceptionFactory, configFilePath, configFileName);

	}

	private void init(ExceptionFactory exceptionFactory,String configFilePath,String configFileName) {
		actionType = ActionType.SmtpMailAction;

		//load the specific property configuration file
		prop = new Properties();
		try {
			prop.load(FileUtil.readFileToInputStream(exceptionFactory, configFilePath, configFileName));	
		}catch (Exception cause) {
			throw exceptionFactory.getImpossibleReadFileException(cause, configFilePath, configFileName);
		}

	}


	@Override
	public void trigger(String message) {

		Session session = null;

		//server info
		prop.put("mail.smtp.host", prop.getProperty("host"));
		prop.put("mail.smtp.port", prop.getProperty("port"));

		//Encrypt logic
		if(prop.getProperty("encryptType").equals("TLS")) {
			prop.put("mail.smtp.starttls.enable", "true");
		}else if (prop.getProperty("encryptType").equals("SSL")) {
			prop.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");			
		}else if (prop.getProperty("encryptType").equals("NONE")) {
			//NO encrypt
		}

		//authentication
		if("true".equals(prop.getProperty("authentication"))) {
			prop.put("mail.smtp.auth", true);
			session = Session.getInstance(prop, 
					new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(prop.getProperty("username"), prop.getProperty("password"));
				}
			});			
		}else {
			session = Session.getInstance(prop);
		}

		Thread thread = new Thread(new SendAsyncMail(session, message), "Thread send mail");
		thread.start();

	}



	class SendAsyncMail implements Runnable{

		private Session session;
		private String message;


		public SendAsyncMail(Session session,String message) {
			super();
			this.session = session;
			this.message = message;
		}

		@Override
		public void run() {
			//send the message
			try {
				log.debug("try to send the mail");
				Message mailMessage = new MimeMessage(session);
				mailMessage.setFrom(new InternetAddress(prop.getProperty("username")));
				mailMessage.setRecipients(Message.RecipientType.TO,InternetAddress.parse(prop.getProperty("username")));
				mailMessage.setSubject("Cryptocurrency Alert!!!!");
				mailMessage.setText(message);
				Transport.send(mailMessage);
			} catch (MessagingException e) {
				log.error("error sending a mail alert", e);
			}

		}

	}

}
