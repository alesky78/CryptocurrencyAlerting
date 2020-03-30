package it.spaghettisource.cryptocurrencyalerting.provider.coinmarketcap;

import java.util.Properties;

import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.WebResource.Builder;

import it.spaghettisource.cryptocurrencyalerting.exception.BaseException;
import it.spaghettisource.cryptocurrencyalerting.exception.ExceptionFactory;
import it.spaghettisource.cryptocurrencyalerting.http.ApacheHttpConnectionFactoryDefault;
import it.spaghettisource.cryptocurrencyalerting.http.CryptoCurrencyHttpJerseyClient;
import it.spaghettisource.cryptocurrencyalerting.utils.FileUtil;

/**
 * Implementation of the http client that support the integration with CoinMarketCap 
 *
 * 
 * @author Alessandro D'Ottavio
 * @version 1.0
 */
public class HttpClientCoinMarketCap extends CryptoCurrencyHttpJerseyClient {


	private String apiKey;



	public HttpClientCoinMarketCap(ExceptionFactory exceptionFactory) {
		this.exceptionFactory = exceptionFactory;
		this.httpsConnectorFactory = new ApacheHttpConnectionFactoryDefault();
		
		init(ConstantCoinMarketCap.CONFIG_FILE_PATH ,ConstantCoinMarketCap.CONFIG_FILE_NAME);
	}

	public HttpClientCoinMarketCap(ExceptionFactory exceptionFactory,String cofigFilePath,String cofigFileName) {
		this.exceptionFactory = exceptionFactory;
		this.httpsConnectorFactory = new ApacheHttpConnectionFactoryDefault();
		init(cofigFilePath,cofigFileName);
	}	
	
	private void init(String cofigFilePath,String cofigFileName) {
		
		httpsConnectorFactory = new ApacheHttpConnectionFactoryDefault();
		
		FileUtil fileUtil = new FileUtil();
		
		Properties prop = new Properties();
		try {
			prop.load(fileUtil.readFileToInputStream(exceptionFactory, cofigFilePath, cofigFileName));	
			apiKey = prop.getProperty("apiKey");
		}catch (Exception cause) {
			throw exceptionFactory.getImpossibleReadFileException(cause, cofigFileName, cofigFilePath);
		}

	}	
	
	@Override
	protected void checkResponseStatusCode(int statusCode) throws BaseException {

		if (statusCode == 400) {
			throw exceptionFactory.getCoinMarketCapHttp400();
		}else if(statusCode == 401) {
			throw exceptionFactory.getCoinMarketCapHttp401();		
		}else if(statusCode == 402) {
			throw exceptionFactory.getCoinMarketCapHttp402();
		}else if(statusCode == 403) {
			throw exceptionFactory.getCoinMarketCapHttp403();
		}else if(statusCode == 429) {
			throw exceptionFactory.getCoinMarketCapHttp429();
		}else if(statusCode == 500) {
			throw exceptionFactory.getCoinMarketCapHttp500();
		}

	}

	@Override
	protected Builder configureRequest(WebResource builder) {
		return builder.header("X-CMC_PRO_API_KEY", apiKey).accept("application/json");
	}

}
