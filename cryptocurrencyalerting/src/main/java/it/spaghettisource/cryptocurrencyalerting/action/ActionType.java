package it.spaghettisource.cryptocurrencyalerting.action;

public enum ActionType {
	
	SmtpMailAction("same label");
	
	private String i18nName;
	
	
	ActionType(String i18nName) {
		this.i18nName = i18nName;
	}
	
	
}
