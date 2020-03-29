package it.spaghettisource.cryptocurrencyalerting.http;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;

import it.spaghettisource.cryptocurrencyalerting.exception.ExceptionFactory;
import it.spaghettisource.cryptocurrencyalerting.i18n.MessageRepository;
import it.spaghettisource.cryptocurrencyalerting.i18n.StringMessageHelper;
import it.spaghettisource.cryptocurrencyalerting.utils.FileUtil;

public class HttpClientCoinMarketCapTest {

	
	ExceptionFactory exceptionFactory = new ExceptionFactory();
	
	@Before
	public void beforeTest() {
		
		//prepare the exception factory
		MessageRepository messageRepository =new MessageRepository();
		messageRepository.setMessageRepositoryBundleBaseName("it.spaghettisource.cryptocurrencyalerting.http.HttpClientCoinMarketCapTest");
		StringMessageHelper helper = new StringMessageHelper();
		helper.setMessageRepository(messageRepository);
		exceptionFactory = new ExceptionFactory();
		exceptionFactory.setMessageHelper(helper);	
		
	}
	
	//confgure the sandbox apiKey that you received bofeore to enable this test or it will fail 
	//@Test
	public void testExampleCall() {

		String cofigFilePath = System.getProperty("user.dir") +"\\src\\test\\resources\\it\\spaghettisource\\cryptocurrencyalerting\\http";
		String cofigFileName = "CoinMarketCapSandbox.properties";
		
		HttpClientCoinMarketCap client = new HttpClientCoinMarketCap(exceptionFactory, cofigFilePath, cofigFileName);
		
		Map<String,String> params = new HashMap<>();
		params.put("start","1");
		params.put("limit","5000");
		params.put("convert","USD");		
		
		
		String response = client.doGet("https://sandbox-api.coinmarketcap.com/v1/cryptocurrency/listings/latest", params);
		System.out.println(response);
		
		FileUtil utils = new FileUtil();

		utils.writeStringToFile(exceptionFactory, cofigFilePath, "response.json",response);
		
		Assert.assertTrue(true);
		
	}
	
	
	
	
	
}
