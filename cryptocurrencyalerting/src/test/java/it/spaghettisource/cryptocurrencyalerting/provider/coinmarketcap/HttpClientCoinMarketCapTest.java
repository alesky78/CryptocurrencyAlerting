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

/**
 * confgure the sandbox apiKey that you received bofeore to enable this test or it will fail
 * 
 * @author Alessandro
 *
 */
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
		String cofigFilePath = System.getProperty("user.dir") +"\\src\\test\\resources\\it\\spaghettisource\\cryptocurrencyalerting\\provider\\coinmarketcap";	//configure in this resource the apiKey
		String cofigFileName = "CoinMarketCapSandbox.properties";
		client = new HttpClientCoinMarketCap(exceptionFactory, cofigFilePath, cofigFileName);
		
	}
	
 
	//@Test
	public void test_OK_FiatMap() {
					
		Map<String,String> params = new HashMap<>();
		params.put("start","1");
		params.put("limit","5000");
		
		
		String response = client.doGet("https://pro-api.coinmarketcap.com/v1/fiat/map",params);

		//writeResponseToFile(response, "HttpClientCoinMarketCapTest_FiatMap.json");
		Assert.assertNotNull(response);
		
	}

	
	//@Test
	public void test_OK_CryptocurrencyMap() {
					
		Map<String,String> params = new HashMap<>();
		params.put("start","1");
		params.put("limit","5000");
		
		String response = client.doGet("https://pro-api.coinmarketcap.com/v1/cryptocurrency/map",params);

		//writeResponseToFile(response, "HttpClientCoinMarketCapTest_CryptocurrencyMap.json");
		Assert.assertNotNull(response);
		
	}

	
	//@Test
	public void test_OK_CryptocurrencyQuatoLatest() {
					
		Map<String,String> params = new HashMap<>();
		params.put("symbol","BTC");
		params.put("convert","EUR");
		
		String response = client.doGet("https://pro-api.coinmarketcap.com/v1/cryptocurrency/quotes/latest",params);
		
		//the model is not properly generic, then we substitute two variable name 

		//writeResponseToFile(response, "HttpClientCoinMarketCapTest_CryptocurrencyQuatoLatest.json");
		Assert.assertNotNull(response);
		
	}	
	
	
	
	private void writeResponseToFile(String response,String fileName) {
		FileUtil utils = new FileUtil();
		String cofigFilePath = System.getProperty("user.dir") +"\\src\\test\\resources\\it\\spaghettisource\\cryptocurrencyalerting\\provider\\coinmarketcap";		
		utils.writeStringToFile(exceptionFactory, cofigFilePath, fileName,response);		
	}
	
	
	
}
