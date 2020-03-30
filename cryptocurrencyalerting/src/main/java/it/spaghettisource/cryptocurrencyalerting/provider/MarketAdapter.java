package it.spaghettisource.cryptocurrencyalerting.provider;

import java.util.List;

import it.spaghettisource.cryptocurrencyalerting.exception.BaseException;

/**
 * Basic interface that expose all the service that are required by a specific provide 
 * the implementation is responsible of the business logic and the correlation of the services exposed by a specific provider in the way to rispect this common interface used by the application
 * 
 * moreover the logic of timing must be implemented in the specific implementation avoiding extra related to the provider
 * for example same provide allow only one call each same period of time, then will be the specific implementation to take care of the expected delay 
 * same other provider allow only a specific amount of call per day/month then the specific implementation should keep care to optimize or cache, when and if is possible, the result of the calls 
 * 
 * @author Alessandro D'Ottavio
 * @version 1.0
 */
public interface MarketAdapter {

	
	/**
	 * get the refresh rate supported
	 * 
	 * @return
	 */
	public int getRefreshRate();
	
	/**
	 * find the full list of Fiat supported by the provider
	 * 
	 * @return all the Fiat supported
	 * @throws BaseException
	 */
	public List<String> findAllFiat() throws BaseException;
	
	
	
	/**
	 * find the full list of Cryptocurrency supported by the provider
	 * 
	 * @return all the Cryptocurrency supported
	 * @throws BaseException
	 */
	public List<String> findAllCryptocurrency() throws BaseException;
	
	
	
	/**
	 * Returns the latest market quote for 1 cryptocurrency converted in a specific fiat
	 * 
	 * @param cryptocurrency to quote
	 * @param fiat to covert 
	 * @return
	 * @throws BaseException
	 */
	public Double quoteLatest(String cryptocurrency,String fiat) throws BaseException;
	
	
}
