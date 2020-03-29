package it.spaghettisource.cryptocurrencyalerting.http;

import java.util.Map;

import it.spaghettisource.cryptocurrencyalerting.exception.BaseException;

/**
 * <code>CryptoCurrencyHttpClient</code> provides interface to method to make http Call
 *
 * 
 * @author Alessandro D'Ottavio
 * @version 1.0
 *
 */
public interface CryptoCurrencyHttpClient {

	
	/**
	 * doGet whit parameters
	 *
	 * @param endpoint
	 * @param queryParams
	 * @throws BaseException 
	 */
	public String doGet(String endpoint,Map<String,String> queryParams) throws BaseException;
	
	/**
	 * doGet without parameters
	 *
	 * @param endpoint
	 * @param queryParams
	 * @throws BaseException 
	 */
	public String doGet(String endpoint) throws BaseException;
	
	/**
	 * doDelete whit parameters
	 *
	 * @param endpoint
	 * @param queryParams
	 * @throws BaseException 
	 */
	public String doDelete(String endpoint,Map<String,String> queryParams) throws BaseException;
	
	/**
	 * doDelete without parameters
	 *
	 * @param endpoint
	 * @param queryParams
	 * @throws BaseException 
	 */
	public String doDelete(String endpoint) throws BaseException;
	
	/**
	 *
	 * @param endpoint
	 * @param body
	 * @return
	 * @throws BaseException 
	 */
	public String doPost(String endpoint,String body) throws BaseException;
	
	/**
	 *
	 * @param endpoint
	 * @param body
	 * @return
	 * @throws BaseException 
	 */
	public String doPut(String endpoint,String body) throws BaseException;
}
