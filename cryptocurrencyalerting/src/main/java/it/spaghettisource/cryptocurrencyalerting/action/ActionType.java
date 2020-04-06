package it.spaghettisource.cryptocurrencyalerting.action;

public enum ActionType {
	
	SmtpMailAction("SmtpMailAction","action.type.SmtpMailAction");
	
	private String i18nKey;
	private String id;	
	
	
	ActionType(String id,String i18nKey) {
		this.i18nKey = i18nKey;
	}

	public String getI18nKey() {
		return i18nKey;
	}
	
    public String getId() {
		return id;
	}

	public static ActionType fromId(String id) {
        for (ActionType value : ActionType.values()) {
            if (value.getId().equalsIgnoreCase(id)) {
                return value;
            }
        }
        return null;
    }

}
