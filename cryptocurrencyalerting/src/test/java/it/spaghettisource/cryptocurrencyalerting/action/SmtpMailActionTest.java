package it.spaghettisource.cryptocurrencyalerting.action;

import it.spaghettisource.cryptocurrencyalerting.action.SmtpMailAction.EncryptType;

public class SmtpMailActionTest {

	//confgure the provider to make the test or it will fail
	//@Test
	public void test_OK_sendMail() {
		
		SmtpMailAction action = new SmtpMailAction();
		
		action.setHost("smtp.gmail.com");
		action.setPort("587");
		
		action.setAuthentication("true");	//if set the autentication set also the user and pwd
		action.setUsername("user@domain.com");
		action.setPassword("pwd");

		action.setSubject("subject");

		action.setEncryptType(EncryptType.TLS);	//set the encrypt to use
		
		action.trigger("this is the content of the mail.... hello from cryptoallert test");
		
	}
		
}
