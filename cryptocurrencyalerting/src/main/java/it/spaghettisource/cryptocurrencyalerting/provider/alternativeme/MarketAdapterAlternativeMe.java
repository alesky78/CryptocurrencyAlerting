package it.spaghettisource.cryptocurrencyalerting.provider.alternativeme;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.spaghettisource.cryptocurrencyalerting.exception.BaseException;
import it.spaghettisource.cryptocurrencyalerting.exception.ExceptionFactory;
import it.spaghettisource.cryptocurrencyalerting.provider.MarketAdapter;
import it.spaghettisource.cryptocurrencyalerting.provider.MarketAdapterAbstract;
import it.spaghettisource.cryptocurrencyalerting.provider.alternativeme.model.Criptocurrency;
import it.spaghettisource.cryptocurrencyalerting.provider.alternativeme.model.Quote.CustomQuoteDeserializer;
import it.spaghettisource.cryptocurrencyalerting.provider.alternativeme.model.ResponseCriptocurrencyTicker;
import it.spaghettisource.cryptocurrencyalerting.provider.alternativeme.model.ResponseCryptocurrencyListing;
import it.spaghettisource.cryptocurrencyalerting.utils.FileUtil;
import it.spaghettisource.cryptocurrencyalerting.utils.JsonConverter;

/**
 * Implementation of the {@link MarketAdapter} of AlternativeMe
 * https://alternative.me/crypto/api/
 *
 * 
 * @author Alessandro D'Ottavio
 * @version 1.0
 */
public class MarketAdapterAlternativeMe extends MarketAdapterAbstract {

	static Logger log = LoggerFactory.getLogger(MarketAdapterAlternativeMe.class);
	
	private HttpClientAlternativeMe httpClient;
	private Properties prop;
	
	public MarketAdapterAlternativeMe(ExceptionFactory exceptionFactory) {
		super(exceptionFactory);
		
		//Create the specific adapter
		httpClient = new HttpClientAlternativeMe(exceptionFactory);

		//load the specific property configuration file
		prop = new Properties();
		try {
			prop.load(FileUtil.readFileToInputStream(exceptionFactory, ConstantAlternativeMe.CONFIG_FILE_PATH, ConstantAlternativeMe.CONFIG_FILE_NAME));	
		}catch (Exception cause) {
			throw exceptionFactory.getImpossibleReadFileException(cause, ConstantAlternativeMe.CONFIG_FILE_PATH, ConstantAlternativeMe.CONFIG_FILE_NAME);
		}
		
	}

	public MarketAdapterAlternativeMe(ExceptionFactory exceptionFactory,HttpClientAlternativeMe httpClient,Properties prop) {
		super(exceptionFactory);
		
		//set the specific adapter
		this.httpClient = httpClient;

		//set the specific configuration
		this.prop = prop;
		
	}	
	
	
	/**
	 * This provider don't expose a service to get the list of FIAT supported then we keep it in its configuration
	 */
	@Override
	public List<String> findAllFiat() throws BaseException {

		 return Collections.list(new StringTokenizer(prop.getProperty("fiatList"), ",")).stream().map(token -> (String) token).collect(Collectors.toList());
						
	}

	@Override
	public List<String> findAllCryptocurrency() throws BaseException {

		String json = httpClient.doGet("https://api.alternative.me/v2/listings/");
		
		log.debug("json response:"+json);
		
		JsonConverter converter = new JsonConverter();
		ResponseCryptocurrencyListing data = converter.jsonToObject(exceptionFactory, json, ResponseCryptocurrencyListing.class);
		
		List<String> response = new ArrayList<String>();
		for (Criptocurrency criptocurrency : data.getData()) {
			response.add(criptocurrency.getName());
		}
		
		return response;
	}

	@Override
	public Double quoteLatest(String cryptocurrency, String fiat) throws BaseException {

		Map<String,String> params = new HashMap<>();
		params.put("convert",fiat);
		
		String json = httpClient.doGet("https://api.alternative.me/v2/ticker/"+cryptocurrency+"/",params);
		
		log.debug("json response:"+json);
		
		CustomQuoteDeserializer deserializer = new CustomQuoteDeserializer();
		deserializer.setQuotePropertyName(fiat);

		JsonConverter converter = new JsonConverter();
		ResponseCriptocurrencyTicker data = converter.jsonToObject(exceptionFactory, json, ResponseCriptocurrencyTicker.class,deserializer,deserializer.getDeserializerClass());
		
		return data.getData().getPrice();
	}

	
	
}




