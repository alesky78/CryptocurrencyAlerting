package it.spaghettisource.cryptocurrencyalerting.alert;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * this Alert is used to verify if the price of a specific criptocurrency goes above or below 
 * a certain price in a specific fiat on the global market  
 * 
 * 
 * 
 * @author Alessandro D'Ottavio
 * @version 1.0
 */
public class PriceVariationGlobalMarketAlert extends AbstractAlert{

	@JsonIgnore
	public static int ABOVE = 1;
	@JsonIgnore
	public static int BELOW = 2;	
	
	private String criptocurency;
	private String fiat;
	private Double price;	
	private Integer mode; //ABOVE or BELOW		
	
	public PriceVariationGlobalMarketAlert() {
		super();
	}

	public PriceVariationGlobalMarketAlert(String criptocurency, String fiat, double price, int mode) {
		this.criptocurency = criptocurency;
		this.fiat = fiat;
		this.price = price;
		this.mode = mode;
	}

	public String getCriptocurency() {
		return criptocurency;
	}

	public void setCriptocurency(String criptocurency) {
		this.criptocurency = criptocurency;
	}

	public String getFiat() {
		return fiat;
	}

	public void setFiat(String fiat) {
		this.fiat = fiat;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getMode() {
		return mode;
	}

	public void setMode(Integer mode) {
		this.mode = mode;
	}

	@Override
	protected boolean checkAndTrigger() {

		Double actualQuote = adapter.quoteLatest(criptocurency, fiat);
		if(mode==ABOVE) {
			if(actualQuote>price) {
				getAction().trigger("the message");
				return true;
			}
		}else if(mode==BELOW) {
			if(actualQuote<price) {
				getAction().trigger("the message");
				return true;
			}
		}
		
		return false;
	}
	
}
