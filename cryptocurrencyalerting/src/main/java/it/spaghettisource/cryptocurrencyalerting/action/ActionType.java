package it.spaghettisource.cryptocurrencyalerting.action;

public enum ActionType {
	
	SmtpMailAction("action.type.SmtpMailAction");
	
	private String i18nKey;
	
	ActionType(String i18nKey) {
		this.i18nKey = i18nKey;
	}

	public String getI18nKey() {
		return i18nKey;
	}

}
