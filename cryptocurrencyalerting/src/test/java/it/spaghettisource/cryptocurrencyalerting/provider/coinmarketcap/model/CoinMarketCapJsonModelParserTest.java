package it.spaghettisource.cryptocurrencyalerting.provider.coinmarketcap.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import it.spaghettisource.cryptocurrencyalerting.exception.ExceptionFactory;
import it.spaghettisource.cryptocurrencyalerting.i18n.MessageRepository;
import it.spaghettisource.cryptocurrencyalerting.i18n.StringMessageHelper;
import it.spaghettisource.cryptocurrencyalerting.utils.FileUtil;
import it.spaghettisource.cryptocurrencyalerting.utils.JsonConverter;

public class CoinMarketCapJsonModelParserTest {

	
	private JsonConverter converter;
	private ExceptionFactory exceptionFactory = new ExceptionFactory();
	private FileUtil utils;
	
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
		utils = new FileUtil();
		
		filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\it\\spaghettisource\\cryptocurrencyalerting\\provider\\coinmarketcap\\model";

		
	}
	
	
	@Test
	public void test_OK_ResponseFiatMap() {
	
		String json = utils.readFileToString(exceptionFactory, filePath, "ResponseFiatMap.json");
		ResponseFiatMap data = converter.jsonToObject(exceptionFactory, json, ResponseFiatMap.class);
		
		Assert.assertNotNull(data);	
	}

	
	@Test
	public void test_OK_ResponseCriptocurrencyMap() {
	
		String json = utils.readFileToString(exceptionFactory, filePath, "ResponseCriptocurrencyMap.json");
		ResponseCriptocurrencyMap data = converter.jsonToObject(exceptionFactory, json, ResponseCriptocurrencyMap.class);
		
		Assert.assertNotNull(data);	
	}
	
}
