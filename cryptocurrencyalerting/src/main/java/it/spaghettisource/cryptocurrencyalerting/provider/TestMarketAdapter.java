package it.spaghettisource.cryptocurrencyalerting.provider;

import java.util.ArrayList;
import java.util.List;

import it.spaghettisource.cryptocurrencyalerting.exception.BaseException;
import it.spaghettisource.cryptocurrencyalerting.exception.ExceptionFactory;

/**
 * Implementation of the {@link MarketAdapter} that simulate the behavior of a real market adapter
 * it is used for test purpose configuring the marketAdapter.class in the configuration.properties file
 * 
 * @author Alessandro D'Ottavio
 * @version 1.0
 */
public class TestMarketAdapter extends MarketAdapterAbstract {

	public TestMarketAdapter(ExceptionFactory exceptionFactory) {
		super(exceptionFactory);
		refreshRateMilliseconds = 20000;
	}
	

	@Override
	public List<String> findAllFiat() throws BaseException {
		ArrayList<String> fiat =new ArrayList<String>();
		fiat.add("EUR");
		fiat.add("USD");
		
		return fiat;
	}

	@Override
	public List<String> findAllCryptocurrency() throws BaseException {
		ArrayList<String> cryptocurrency =new ArrayList<String>();
		cryptocurrency.add("BTC");
		cryptocurrency.add("ETH");
		cryptocurrency.add("XRP");		
		
		return cryptocurrency;
	}

	@Override
	public Double quoteLatest(String cryptocurrency, String fiat) throws BaseException {
		
		return 1000D;
	}

}
