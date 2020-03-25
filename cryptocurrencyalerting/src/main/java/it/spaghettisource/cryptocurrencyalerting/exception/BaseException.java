package it.spaghettisource.cryptocurrencyalerting.exception;


import java.util.Locale;

import it.spaghettisource.cryptocurrencyalerting.i18n.I18NMessageHelper;
import it.spaghettisource.cryptocurrencyalerting.i18n.StringMessageHelper;

/**
 * Superclass of all Exceptions used
 * 
 * @author Alessandro D'Ottavio
 * @version 1.0
 *
 */
public class BaseException extends RuntimeException {

	protected String errorCode;  
	protected Object[] messageParameters;
	
	protected I18NMessageHelper messageHelper;
	
	public BaseException(Throwable cause,String errorCode,Object... messageParameters ) {
		super(cause);
		this.errorCode = errorCode;
		this.messageParameters = messageParameters;
	}

	public BaseException(String errorCode,Object...  messageParameters ) {
		super();
		this.errorCode = errorCode;
		this.messageParameters = messageParameters;
	}

	public void setMessageHelper(StringMessageHelper messageHelper) {
		this.messageHelper = messageHelper;
	}

	@Override
	public String getMessage(){		
		return messageHelper.getFormattedMessageI18N(errorCode, messageParameters);		 
	}

	public String getMessage(Locale locale){		
		return messageHelper.getFormattedMessageI18N(locale, errorCode, messageParameters);
	}
	
	
}
