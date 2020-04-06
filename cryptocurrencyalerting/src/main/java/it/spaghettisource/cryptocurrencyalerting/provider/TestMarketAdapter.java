package it.spaghettisource.cryptocurrencyalerting.provider;

import java.util.ArrayList;
import java.util.List;

import it.spaghettisource.cryptocurrencyalerting.exception.BaseException;
import it.spaghettisource.cryptocurrencyalerting.exception.ExceptionFactory;

public class TestMarketAdapter extends MarketAdapterAbstract {

	public TestMarketAdapter(ExceptionFactory exceptionFactory) {
		super(exceptionFactory);

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

		return null;
	}

}
