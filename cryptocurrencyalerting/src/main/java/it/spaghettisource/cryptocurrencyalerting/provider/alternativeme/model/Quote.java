package it.spaghettisource.cryptocurrencyalerting.provider.alternativeme.model;

import java.io.IOException;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Quote {

	private Double price;
	private Double volume_24h;	
	private Double percent_change_1h;	
	private Double percent_change_24h;
	private Double percent_change_7d;
	private Double market_cap;
	
	
	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getVolume_24h() {
		return volume_24h;
	}

	public void setVolume_24h(Double volume_24h) {
		this.volume_24h = volume_24h;
	}

	public Double getPercent_change_1h() {
		return percent_change_1h;
	}

	public void setPercent_change_1h(Double percent_change_1h) {
		this.percent_change_1h = percent_change_1h;
	}

	public Double getPercent_change_24h() {
		return percent_change_24h;
	}

	public void setPercent_change_24h(Double percent_change_24h) {
		this.percent_change_24h = percent_change_24h;
	}

	public Double getPercent_change_7d() {
		return percent_change_7d;
	}

	public void setPercent_change_7d(Double percent_change_7d) {
		this.percent_change_7d = percent_change_7d;
	}

	public Double getMarket_cap() {
		return market_cap;
	}

	public void setMarket_cap(Double market_cap) {
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
			
			
			quote.setPercent_change_1h(quoteNode.get("percentage_change_1h").asDouble());
			quote.setPercent_change_24h(quoteNode.get("percentage_change_24h").asDouble());
			quote.setPercent_change_7d(quoteNode.get("percentage_change_7d").asDouble());			
			quote.setMarket_cap(quoteNode.get("market_cap").asDouble());
			
			return quote;
		}
		
	}

	
	
	
}
