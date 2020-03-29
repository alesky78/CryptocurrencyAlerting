package it.spaghettisource.cryptocurrencyalerting.http;

import org.apache.http.client.HttpClient;

import it.spaghettisource.cryptocurrencyalerting.exception.BaseException;
import it.spaghettisource.cryptocurrencyalerting.exception.ExceptionFactory;

public interface ApacheHttpConnectionFactory {

	public HttpClient builApacheHttpsClient(ExceptionFactory exceptionFactory) throws BaseException ;
	
	
	
}
