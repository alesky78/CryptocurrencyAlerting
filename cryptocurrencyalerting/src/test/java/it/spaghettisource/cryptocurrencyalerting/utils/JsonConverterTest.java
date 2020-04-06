package it.spaghettisource.cryptocurrencyalerting.utils;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import it.spaghettisource.cryptocurrencyalerting.alert.PriceVariationGlobalMarketAlert;
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
		
		PriceVariationGlobalMarketAlert action = new PriceVariationGlobalMarketAlert();
	
		String json = converter.objectToJson(exceptionFactory, action);
		
		System.out.println(json);
		Assert.assertNotNull(json);
	}
	
	
	@Test
	public void test_OK_JsonToObject() {
		
		String json = "{ \"id\" : \"1586177973318\",\r\n" + 
				"  \"alertType\" : \"PriceVariationGlobalMarketAlert\",\r\n" + 
				"  \"disable\" : false,\r\n" + 
				"  \"disableAfterTrigger\" : false,\r\n" + 
				"  \"coolDown\" : false,\r\n" + 
				"  \"enableCoolDown\" : true,\r\n" + 
				"  \"coolDownMinuts\" : 60,\r\n" + 
				"  \"actionType\" : null,\r\n" + 
				"  \"criptocurency\" : \"BTC\",\r\n" + 
				"  \"fiat\" : \"USD\",\r\n" + 
				"  \"price\" : 0.0,\r\n" + 
				"  \"mode\" : \"1\",\r\n" + 
				"  \"lastTriggerSecond\" : -1,\r\n" + 
				"  \"timePassSinceLastTriggerSecond\" : -1\r\n" + 
				"}";
		PriceVariationGlobalMarketAlert action = converter.jsonToObject(exceptionFactory, json, PriceVariationGlobalMarketAlert.class);
		System.out.println(action);
		Assert.assertNotNull(action);
	}
	
	
	@Test(expected = BaseException.class)
	public void test_KO_JsonToObject() {
		
		String json = "{\"encryptType\":\"NONE\",\"username\":\"user\",\"password\":\"pwd\",\"host\":\"mail.host.com\",\"port\":null,\"authentication\":\"true\",\"subject\":null}";
		converter.jsonToObject(exceptionFactory, json, ArrayList.class);			
		
	}

	
}
