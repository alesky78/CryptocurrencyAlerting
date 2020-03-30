package it.spaghettisource.cryptocurrencyalerting.provider.coinmarketcap.model;

import java.io.IOException;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Quote {

	private double price;
	private double volume_24h;	
	private double percent_change_1h;	
	private double percent_change_24h;
	private double percent_change_7d;
	private double market_cap;
	
	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getVolume_24h() {
		return volume_24h;
	}

	public void setVolume_24h(double volume_24h) {
		this.volume_24h = volume_24h;
	}

	public double getPercent_change_1h() {
		return percent_change_1h;
	}

	public void setPercent_change_1h(double percent_change_1h) {
		this.percent_change_1h = percent_change_1h;
	}

	public double getPercent_change_24h() {
		return percent_change_24h;
	}

	public void setPercent_change_24h(double percent_change_24h) {
		this.percent_change_24h = percent_change_24h;
	}

	public double getPercent_change_7d() {
		return percent_change_7d;
	}

	public void setPercent_change_7d(double percent_change_7d) {
		this.percent_change_7d = percent_change_7d;
	}

	public double getMarket_cap() {
		return market_cap;
	}

	public void setMarket_cap(double market_cap) {
		this.market_cap = market_cap;
	}



	public static  class CustomQuoteDeserializer extends StdDeserializer<Quote>{

		private String quotePropertyName;
		
		public void setQuotePropertyName(String quotePropertyName) {
			this.quotePropertyName = quotePropertyName;
		}
		
		public Class<Quote> getDeserializerClass(){
			return Quote.class;
		}
		
		public CustomQuoteDeserializer(){
	        this(null);
	    }
	    public CustomQuoteDeserializer(Class<?> c){
	        super(c);
	    }

		@Override
		public Quote deserialize(JsonParser jp, DeserializationContext ctxt)throws IOException, JsonProcessingException {

			Quote quote = new Quote();
			
			JsonNode dataNode = jp.getCodec().readTree(jp);
			JsonNode quoteNode = dataNode.findValue(quotePropertyName);
			
			quote.setPrice(quoteNode.get("price").asDouble());
			quote.setVolume_24h(quoteNode.get("volume_24h").asDouble());
			quote.setPercent_change_1h(quoteNode.get("percent_change_1h").asDouble());
			quote.setPercent_change_24h(quoteNode.get("percent_change_24h").asDouble());
			quote.setPercent_change_7d(quoteNode.get("percent_change_7d").asDouble());			
			quote.setMarket_cap(quoteNode.get("market_cap").asDouble());
			
			return quote;
		}
		
	}

	
	
	
}
