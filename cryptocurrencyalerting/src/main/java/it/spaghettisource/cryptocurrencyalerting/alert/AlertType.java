package it.spaghettisource.cryptocurrencyalerting.alert;

public enum AlertType {
	
	PriceVariationGlobalMarketAlert("PriceVariationGlobalMarketAlert","alert.type.PriceVariationGlobalMarketAlert");
	
	private String id;	
	private String i18nKey;
	
	AlertType(String id,String i18nKey) {
		this.id = id;
		this.i18nKey = i18nKey;
	}

	
	public String getI18nKey() {
		return i18nKey;
	}
	
    public String getId() {
		return id;
	}
    
    
	public static AlertType fromId(String id) {
        for (AlertType value : AlertType.values()) {
            if (value.getId().equalsIgnoreCase(id)) {
                return value;
            }
        }
        return null;
    }


	
}
