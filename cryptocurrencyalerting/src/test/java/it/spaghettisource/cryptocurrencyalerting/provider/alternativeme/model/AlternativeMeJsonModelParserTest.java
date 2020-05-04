package it.spaghettisource.cryptocurrencyalerting.provider.alternativeme.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import it.spaghettisource.cryptocurrencyalerting.exception.ExceptionFactory;
import it.spaghettisource.cryptocurrencyalerting.i18n.MessageRepository;
import it.spaghettisource.cryptocurrencyalerting.i18n.StringMessageHelper;
import it.spaghettisource.cryptocurrencyalerting.provider.alternativeme.model.Quote.CustomQuoteDeserializer;
import it.spaghettisource.cryptocurrencyalerting.utils.FileUtil;
import it.spaghettisource.cryptocurrencyalerting.utils.JsonConverter;

public class AlternativeMeJsonModelParserTest {

	
	private JsonConverter converter;
	private ExceptionFactory exceptionFactory = new ExceptionFactory();
	
	private String filePath;
	
	@Before
	public void beforeTest() {
		
		//prepare the exception factory
		MessageRepository messageRepository =new MessageRepository();
		messageRepository.setMessageRepositoryBundleBaseName("it.spaghettisource.cryptocurrencyalerting.provider.alternativeme.model.AlternativeMeJsonModelParserTest");
		StringMessageHelper helper = new StringMessageHelper();
		helper.setMessageRepository(messageRepository);
		exceptionFactory = new ExceptionFactory();
		exceptionFactory.setMessageHelper(helper);
		
		converter = new JsonConverter();
		
		filePath = System.getProperty("user.dir")+"/src/test/resources/it/spaghettisource/cryptocurrencyalerting/provider/alternativeme/model";

		
	}

	
	@Test
	public void test_OK_ResponseCryptocurrencyListing() {
	
		String json = FileUtil.readFileToString(exceptionFactory, filePath, "ResponseCriptocurrencyListing.json");
		ResponseCryptocurrencyListing data = converter.jsonToObject(exceptionFactory, json, ResponseCryptocurrencyListing.class);
		
		Assert.assertNotNull(data);	
	}

	@Test
	public void test_OK_ResponseCriptocurrencyTicker() {
	
		String json = FileUtil.readFileToString(exceptionFactory, filePath, "ResponseCriptocurrencyTicker.json");
		
		CustomQuoteDeserializer deserializer = new CustomQuoteDeserializer();
		deserializer.setQuotePropertyName("USD");
		
		ResponseCriptocurrencyTicker data = converter.jsonToObject(exceptionFactory, json, ResponseCriptocurrencyTicker.class,deserializer,deserializer.getDeserializerClass());
		
		Assert.assertNotNull(data);	

	}	
		
	
}
