package it.spaghettisource.cryptocurrencyalerting.provider.coinmarketcap;

import java.util.List;
import java.util.Properties;

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
public class MarketAdapterCoinMarketCapTest {

	
	private MarketAdapterCoinMarketCap adapter;
	
	@Before
	public void beforeTest() {
		
		//prepare the exception factory
		MessageRepository messageRepository = new MessageRepository();
		messageRepository.setMessageRepositoryBundleBaseName("it.spaghettisource.cryptocurrencyalerting.provider.coinmarketcap.HttpClientCoinMarketCapTest");
		StringMessageHelper helper = new StringMessageHelper();
		helper.setMessageRepository(messageRepository);
		ExceptionFactory exceptionFactory = new ExceptionFactory();
		exceptionFactory.setMessageHelper(helper);
		
		
		
		//create the CoinMarketCap client
		String cofigFilePath = System.getProperty("user.dir") +"\\src\\test\\resources\\it\\spaghettisource\\cryptocurrencyalerting\\provider\\coinmarketcap";	//configure in this resource the apiKey
		String cofigFileName = "CoinMarketCap.properties";
		HttpClientCoinMarketCap httpClient = new HttpClientCoinMarketCap(exceptionFactory, cofigFilePath, cofigFileName);
		
		//create the properties
		Properties prop = new Properties();
		try {
			prop.load(FileUtil.readFileToInputStream(exceptionFactory, cofigFilePath, cofigFileName));	
		}catch (Exception cause) {
			throw exceptionFactory.getImpossibleReadFileException(cause, cofigFilePath, cofigFileName);
		}
		
		
		adapter = new MarketAdapterCoinMarketCap(exceptionFactory, httpClient, prop);
		
		
	}
	
	
	//@Test
	public void test_OK_FindAllCriptocurrency() {
		List<String> criptocurrencies = adapter.findAllCryptocurrency();
		Assert.assertTrue(criptocurrencies.size()>0);
		
	}

	//@Test
	public void test_OK_FindAllFiat() {
		List<String> fiat = adapter.findAllFiat();
		Assert.assertTrue(fiat.size()>0);
	}	
	
	//@Test
	public void test_OK_QuoteLatest() {
		Double quote = adapter.quoteLatest("BTC", "EUR");
		Assert.assertNotNull(quote);
	}	
	
	
}
