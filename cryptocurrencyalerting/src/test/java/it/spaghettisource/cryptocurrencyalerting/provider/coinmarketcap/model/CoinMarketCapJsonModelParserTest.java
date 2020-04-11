package it.spaghettisource.cryptocurrencyalerting.provider.coinmarketcap.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import it.spaghettisource.cryptocurrencyalerting.exception.ExceptionFactory;
import it.spaghettisource.cryptocurrencyalerting.i18n.MessageRepository;
import it.spaghettisource.cryptocurrencyalerting.i18n.StringMessageHelper;
import it.spaghettisource.cryptocurrencyalerting.provider.coinmarketcap.model.Quote.CustomQuoteDeserializer;
import it.spaghettisource.cryptocurrencyalerting.utils.FileUtil;
import it.spaghettisource.cryptocurrencyalerting.utils.JsonConverter;

public class CoinMarketCapJsonModelParserTest {

	
	private JsonConverter converter;
	private ExceptionFactory exceptionFactory = new ExceptionFactory();
	
	private String filePath;
	
	@Before
	public void beforeTest() {
		
		//prepare the exception factory
		MessageRepository messageRepository =new MessageRepository();
		messageRepository.setMessageRepositoryBundleBaseName("it.spaghettisource.cryptocurrencyalerting.provider.coinmarketcap.model.CoinMarketCapJsonModelParserTest");
		StringMessageHelper helper = new StringMessageHelper();
		helper.setMessageRepository(messageRepository);
		exceptionFactory = new ExceptionFactory();
		exceptionFactory.setMessageHelper(helper);
		
		converter = new JsonConverter();
		
		filePath = System.getProperty("user.dir")+"/src/test/resources/it/spaghettisource/cryptocurrencyalerting/provider/coinmarketcap/model";

		
	}
	
	
	@Test
	public void test_OK_ResponseFiatMap() {
	
		String json = FileUtil.readFileToString(exceptionFactory, filePath, "ResponseFiatMap.json");
		ResponseFiatMap data = converter.jsonToObject(exceptionFactory, json, ResponseFiatMap.class);
		
		Assert.assertNotNull(data);	
	}

	
	@Test
	public void test_OK_ResponseCriptocurrencyMap() {
	
		String json = FileUtil.readFileToString(exceptionFactory, filePath, "ResponseCriptocurrencyMap.json");
		ResponseCriptocurrencyMap data = converter.jsonToObject(exceptionFactory, json, ResponseCriptocurrencyMap.class);
		
		Assert.assertNotNull(data);	
	}

	
	@Test
	public void test_OK_ResponseCriptocurrencyQuoteLatest() {
	
		String json = FileUtil.readFileToString(exceptionFactory, filePath, "ResponseCryptocurrencyQuatoLatest.json");
		
		CustomQuoteDeserializer deserializer = new CustomQuoteDeserializer();
		deserializer.setQuotePropertyName("EUR");
		
		ResponseCriptocurrencyQuoteLatest data = converter.jsonToObject(exceptionFactory, json, ResponseCriptocurrencyQuoteLatest.class,deserializer,deserializer.getDeserializerClass());
		
		Assert.assertNotNull(data);	

	}	
	
	
}
