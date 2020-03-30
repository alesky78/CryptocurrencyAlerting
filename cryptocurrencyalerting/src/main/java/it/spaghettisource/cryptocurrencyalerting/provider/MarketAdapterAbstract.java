package it.spaghettisource.cryptocurrencyalerting.provider;

import it.spaghettisource.cryptocurrencyalerting.exception.ExceptionFactory;

/**
 * Basic implementation of the {@link MarketAdapter} to force the main constructor 
 * 
 *
 * 
 * @author Alessandro D'Ottavio
 * @version 1.0
 */
public abstract class MarketAdapterAbstract implements MarketAdapter {

	protected ExceptionFactory exceptionFactory;
	protected int refreshRateMilliseconds;

	
	public MarketAdapterAbstract(ExceptionFactory exceptionFactory) {
		super();
		this.exceptionFactory = exceptionFactory;
	}
	
	
	public int getRefreshRate() {
		return refreshRateMilliseconds;
	}
	

}
