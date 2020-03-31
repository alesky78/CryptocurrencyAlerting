package it.spaghettisource.cryptocurrencyalerting.utils;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import it.spaghettisource.cryptocurrencyalerting.action.SmtpMailAction;
import it.spaghettisource.cryptocurrencyalerting.action.SmtpMailAction.EncryptType;
import it.spaghettisource.cryptocurrencyalerting.exception.BaseException;
import it.spaghettisource.cryptocurrencyalerting.exception.ExceptionFactory;
import it.spaghettisource.cryptocurrencyalerting.i18n.MessageRepository;
import it.spaghettisource.cryptocurrencyalerting.i18n.StringMessageHelper;

public class JsonConverterTest {

	
	private JsonConverter converter;
	private ExceptionFactory exceptionFactory = new ExceptionFactory();
	
	@Before
	public void beforeTest() {
		//prepare the repository
		converter = new JsonConverter();
		
		//prepare the exception factory
		MessageRepository repository =new MessageRepository();
		repository.setMessageRepositoryBundleBaseName("it.spaghettisource.cryptocurrencyalerting.utils.JsonConverterTest");
		StringMessageHelper helper = new StringMessageHelper();
		helper.setMessageRepository(repository);
		exceptionFactory = new ExceptionFactory();
		exceptionFactory.setMessageHelper(helper);
		
	}
	
	
	@Test
	public void test_OK_convertObjectToJson() {
		
		SmtpMailAction action = new SmtpMailAction();
		action.setAuthentication("true");
		action.setUsername("user");
		action.setPassword("pwd");
		action.setEncryptType(EncryptType.NONE);
		action.setHost("mail.host.com");
	
		String json = converter.objectToJson(exceptionFactory, action);
		
		System.out.println(json);
		Assert.assertNotNull(json);
	}
	
	
	@Test
	public void test_OK_JsonToObject() {
		
		String json = "{\"encryptType\":\"NONE\",\"username\":\"user\",\"password\":\"pwd\",\"host\":\"mail.host.com\",\"port\":null,\"authentication\":\"true\",\"subject\":null}";
		SmtpMailAction action = converter.jsonToObject(exceptionFactory, json, SmtpMailAction.class);
		System.out.println(action);
		Assert.assertNotNull(action);
	}
	
	
	@Test(expected = BaseException.class)
	public void test_KO_JsonToObject() {
		
		String json = "{\"encryptType\":\"NONE\",\"username\":\"user\",\"password\":\"pwd\",\"host\":\"mail.host.com\",\"port\":null,\"authentication\":\"true\",\"subject\":null}";
		converter.jsonToObject(exceptionFactory, json, ArrayList.class);			
		
	}

	
}
