package it.spaghettisource.cryptocurrencyalerting.alert;

public enum AlertType {
	
	PriceVariationGlobalMarketAlert("alert.type.PriceVariationGlobalMarketAlert");
	
	
	private String i18nKey;
	
	AlertType(String i18nKey) {
		this.i18nKey = i18nKey;
	}

	public String getI18nKey() {
		return i18nKey;
	}

	
}
