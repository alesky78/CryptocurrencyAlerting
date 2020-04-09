package it.spaghettisource.cryptocurrencyalerting.alert;

import com.fasterxml.jackson.annotation.JsonIgnore;

import it.spaghettisource.cryptocurrencyalerting.i18n.StringMessageHelper;
import it.spaghettisource.cryptocurrencyalerting.provider.MarketAdapter;
import it.spaghettisource.cryptocurrencyalerting.repository.PriceVariationGlobalMarketAlertRepository;
import it.spaghettisource.cryptocurrencyalerting.services.ServiceLocator;

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
	public static String ABOVE = "1";
	public static String ABOVE_I18N = "alert.pricevariationglobalmarketalert.abow";	
	@JsonIgnore
	public static String BELOW = "2";
	public static String BELOW_I18N = "alert.pricevariationglobalmarketalert.below";	
	
	private String criptocurency;
	private String fiat;
	private Double price;	
	private String mode; //ABOVE or BELOW		
	
	public PriceVariationGlobalMarketAlert() {
		super();
		alertType = AlertType.PriceVariationGlobalMarketAlert;
		

		
	}

	public PriceVariationGlobalMarketAlert(String criptocurency, String fiat, double price, String mode) {
		super();
		alertType = AlertType.PriceVariationGlobalMarketAlert;
		
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

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	@Override
	protected boolean checkAndTrigger() {
		
		MarketAdapter adapter = ServiceLocator.getInstance().getMarketAdapter();
		
		Double actualQuote = adapter.quoteLatest(criptocurency, fiat);
		if(mode.equals(ABOVE)) {
			if(actualQuote>price) {
				getAction().trigger(createAlertMessage(actualQuote));
				return true;
			}
		}else if(mode.equals(BELOW)) {
			if(actualQuote<price) {
				getAction().trigger(createAlertMessage(actualQuote));
				return true;
			}
		}
		
		return false;
	}
	
	private String createAlertMessage(Double actualQuote) {
		
		StringMessageHelper messageHelper  = ServiceLocator.getInstance().getMessageHelper();
		
		String modeMessage = null;
		
		if(mode.equals(ABOVE)) {
			modeMessage = messageHelper.getFormattedMessageI18N(ABOVE_I18N, null); 
		}else {
			modeMessage = messageHelper.getFormattedMessageI18N(BELOW_I18N, null); 			
		}
		
		Object[] parameters = new Object[] {criptocurency,fiat,actualQuote,modeMessage,price};
		return messageHelper.getFormattedMessageI18N("alert.pricevariationglobalmarketalert.message", parameters);
	}

	
	@Override
	protected void storeUpdatedData() {

		PriceVariationGlobalMarketAlertRepository repository = new PriceVariationGlobalMarketAlertRepository(ServiceLocator.getInstance().getExceptionFactory());
		repository.update(this);
		
	}
	
}
