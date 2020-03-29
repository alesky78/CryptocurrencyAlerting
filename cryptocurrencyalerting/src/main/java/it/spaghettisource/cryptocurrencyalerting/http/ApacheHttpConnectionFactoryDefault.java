package it.spaghettisource.cryptocurrencyalerting.http;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClients;

import it.spaghettisource.cryptocurrencyalerting.exception.BaseException;
import it.spaghettisource.cryptocurrencyalerting.exception.ExceptionFactory;

/**
 * basic implementatio
 * 
 * @author Alessandro
 *
 */
public class ApacheHttpConnectionFactoryDefault implements ApacheHttpConnectionFactory{


	/**
	 * basic implementation of the http client
	 *
	 * @return
	 */
	public HttpClient builApacheHttpsClient(ExceptionFactory exceptionFactory) throws BaseException{

		return HttpClients.createDefault();
	}

}
