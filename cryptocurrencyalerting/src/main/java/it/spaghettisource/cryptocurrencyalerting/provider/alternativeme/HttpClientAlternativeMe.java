package it.spaghettisource.cryptocurrencyalerting.provider.alternativeme;

import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.WebResource.Builder;

import it.spaghettisource.cryptocurrencyalerting.exception.BaseException;
import it.spaghettisource.cryptocurrencyalerting.exception.ExceptionFactory;
import it.spaghettisource.cryptocurrencyalerting.http.ApacheHttpConnectionFactoryDefault;
import it.spaghettisource.cryptocurrencyalerting.http.CryptoCurrencyHttpJerseyClient;

/**
 * Implementation of the http client that support the integration with AlternativeMe 
 *
 * 
 * @author Alessandro D'Ottavio
 * @version 1.0
 */
public class HttpClientAlternativeMe extends CryptoCurrencyHttpJerseyClient {



	public HttpClientAlternativeMe(ExceptionFactory exceptionFactory) {
		this.exceptionFactory = exceptionFactory;
		this.httpsConnectorFactory = new ApacheHttpConnectionFactoryDefault();
		
		init(ConstantAlternativeMe.CONFIG_FILE_PATH ,ConstantAlternativeMe.CONFIG_FILE_NAME);
	}

	public HttpClientAlternativeMe(ExceptionFactory exceptionFactory,String cofigFilePath,String cofigFileName) {
		this.exceptionFactory = exceptionFactory;
		this.httpsConnectorFactory = new ApacheHttpConnectionFactoryDefault();
		
		init(cofigFilePath,cofigFileName);
	}	
	
	private void init(String cofigFilePath,String cofigFileName) {
		httpsConnectorFactory = new ApacheHttpConnectionFactoryDefault();
	}	
	
	@Override
	protected void checkResponseStatusCode(int statusCode) throws BaseException {

		if (statusCode != 200) {
			throw exceptionFactory.getAlternativeMeHttpError(statusCode);
		}

	}

	@Override
	protected Builder configureRequest(WebResource builder) {
		return builder.accept("application/json");
	}

}
