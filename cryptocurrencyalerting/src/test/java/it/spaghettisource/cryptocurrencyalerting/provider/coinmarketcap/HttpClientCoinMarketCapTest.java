package it.spaghettisource.cryptocurrencyalerting.provider.coinmarketcap;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import it.spaghettisource.cryptocurrencyalerting.exception.ExceptionFactory;
import it.spaghettisource.cryptocurrencyalerting.i18n.MessageRepository;
import it.spaghettisource.cryptocurrencyalerting.i18n.StringMessageHelper;
import it.spaghettisource.cryptocurrencyalerting.utils.FileUtil;

public class HttpClientCoinMarketCapTest {

	
	private ExceptionFactory exceptionFactory = new ExceptionFactory();
	private HttpClientCoinMarketCap client;
	
	@Before
	public void beforeTest() {
		
		//prepare the exception factory
		MessageRepository messageRepository =new MessageRepository();
		messageRepository.setMessageRepositoryBundleBaseName("it.spaghettisource.cryptocurrencyalerting.provider.coinmarketcap.HttpClientCoinMarketCapTest");
		StringMessageHelper helper = new StringMessageHelper();
		helper.setMessageRepository(messageRepository);
		exceptionFactory = new ExceptionFactory();
		exceptionFactory.setMessageHelper(helper);
		
		//create the CoinMarketCap client
		String cofigFilePath = System.getProperty("user.dir") +"\\src\\test\\resources\\it\\spaghettisource\\cryptocurrencyalerting\\provider\\coinmarketcap";
		String cofigFileName = "CoinMarketCapSandbox.properties";
		client = new HttpClientCoinMarketCap(exceptionFactory, cofigFilePath, cofigFileName);
		
	}
	
	//confgure the sandbox apiKey that you received bofeore to enable this test or it will fail 
	@Test
	public void testExampleCall() {
		
		Map<String,String> params = new HashMap<>();
		params.put("start","1");
		params.put("limit","5000");
		params.put("convert","USD");		
		
		
		String response = client.doGet("https://sandbox-api.coinmarketcap.com/v1/cryptocurrency/listings/latest", params);

		writeResponseToFile(response, "HttpClientCoinMarketCapTestCall.json");
		Assert.assertNotNull(response);
		
	}
	
	
	private void writeResponseToFile(String response,String fileName) {
		FileUtil utils = new FileUtil();
		String cofigFilePath = System.getProperty("user.dir") +"\\src\\test\\resources\\it\\spaghettisource\\cryptocurrencyalerting\\provider\\coinmarketcap";		
		utils.writeStringToFile(exceptionFactory, cofigFilePath, "HttpClientCoinMarketCapTestResponse.json",response);		
	}
	
	
	
}
