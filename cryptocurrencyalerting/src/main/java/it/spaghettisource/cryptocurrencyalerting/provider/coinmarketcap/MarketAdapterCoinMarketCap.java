package it.spaghettisource.cryptocurrencyalerting.provider.coinmarketcap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import it.spaghettisource.cryptocurrencyalerting.exception.BaseException;
import it.spaghettisource.cryptocurrencyalerting.exception.ExceptionFactory;
import it.spaghettisource.cryptocurrencyalerting.provider.MarketAdapter;
import it.spaghettisource.cryptocurrencyalerting.provider.MarketAdapterAbstract;
import it.spaghettisource.cryptocurrencyalerting.provider.coinmarketcap.model.Criptocurrency;
import it.spaghettisource.cryptocurrencyalerting.provider.coinmarketcap.model.Fiat;
import it.spaghettisource.cryptocurrencyalerting.provider.coinmarketcap.model.ResponseCriptocurrencyMap;
import it.spaghettisource.cryptocurrencyalerting.provider.coinmarketcap.model.ResponseCriptocurrencyQuoteLatest;
import it.spaghettisource.cryptocurrencyalerting.provider.coinmarketcap.model.ResponseFiatMap;
import it.spaghettisource.cryptocurrencyalerting.provider.coinmarketcap.model.Quote.CustomQuoteDeserializer;
import it.spaghettisource.cryptocurrencyalerting.utils.FileUtil;
import it.spaghettisource.cryptocurrencyalerting.utils.JsonConverter;

/**
 * Implementation of the {@link MarketAdapter} of CoinMarketCap
 *
 * 
 * @author Alessandro D'Ottavio
 * @version 1.0
 */
public class MarketAdapterCoinMarketCap extends MarketAdapterAbstract {

	private HttpClientCoinMarketCap httpClient;
	private Properties prop;
	
	public MarketAdapterCoinMarketCap(ExceptionFactory exceptionFactory) {
		super(exceptionFactory);
		
		//Create the specific adapter
		httpClient = new HttpClientCoinMarketCap(exceptionFactory);

		//load the specific property configuration file
		prop = new Properties();
		try {
			prop.load(FileUtil.readFileToInputStream(exceptionFactory, ConstantCoinMarketCap.CONFIG_FILE_PATH, ConstantCoinMarketCap.CONFIG_FILE_NAME));	
		}catch (Exception cause) {
			throw exceptionFactory.getImpossibleReadFileException(cause, ConstantCoinMarketCap.CONFIG_FILE_PATH, ConstantCoinMarketCap.CONFIG_FILE_NAME);
		}
		
	}

	public MarketAdapterCoinMarketCap(ExceptionFactory exceptionFactory,HttpClientCoinMarketCap httpClient,Properties prop) {
		super(exceptionFactory);
		
		//set the specific adapter
		this.httpClient = httpClient;

		//set the specific configuration
		this.prop = prop;
		
	}	
	
	
	@Override
	public List<String> findAllFiat() throws BaseException {

		Map<String,String> params = new HashMap<>();
		params.put("start","1");
		params.put("limit","5000");
		
		String json = httpClient.doGet("https://pro-api.coinmarketcap.com/v1/fiat/map",params);
		
		JsonConverter converter = new JsonConverter();
		ResponseFiatMap data = converter.jsonToObject(exceptionFactory, json, ResponseFiatMap.class);
		
		List<String> response = new ArrayList<String>();
		for (Fiat fiat : data.getData()) {
			response.add(fiat.getSymbol());
		}
		
		return response;
	}

	@Override
	public List<String> findAllCryptocurrency() throws BaseException {

		Map<String,String> params = new HashMap<>();
		params.put("start","1");
		params.put("limit","5000");
		
		String json = httpClient.doGet("https://pro-api.coinmarketcap.com/v1/cryptocurrency/map",params);
		
		JsonConverter converter = new JsonConverter();
		ResponseCriptocurrencyMap data = converter.jsonToObject(exceptionFactory, json, ResponseCriptocurrencyMap.class);
		
		List<String> response = new ArrayList<String>();
		for (Criptocurrency criptocurrency : data.getData()) {
			response.add(criptocurrency.getSymbol());
		}
		
		return response;
	}

	@Override
	public Double quoteLatest(String cryptocurrency, String fiat) throws BaseException {

		Map<String,String> params = new HashMap<>();
		params.put("symbol",cryptocurrency);
		params.put("convert",fiat);
		
		String json = httpClient.doGet("https://pro-api.coinmarketcap.com/v1/cryptocurrency/quotes/latest",params);
		
		CustomQuoteDeserializer deserializer = new CustomQuoteDeserializer();
		deserializer.setQuotePropertyName(fiat);

		JsonConverter converter = new JsonConverter();
		ResponseCriptocurrencyQuoteLatest data = converter.jsonToObject(exceptionFactory, json, ResponseCriptocurrencyQuoteLatest.class,deserializer,deserializer.getDeserializerClass());
		
		return data.getData().getPrice();
	}

}
