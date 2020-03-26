package it.spaghettisource.cryptocurrencyalerting.exception;


import it.spaghettisource.cryptocurrencyalerting.i18n.I18NMessageHelper;


/**
 * 
 * Factory class used to create exception, this class allow to format the correct error message and hide the error code 
 * avoiding to put the error code in the business code
 * 
 * @author Alessando D'Ottavio
 *
 */
public class ExceptionFactory {


	private I18NMessageHelper messageHelper;
	
	public void setMessageHelper(I18NMessageHelper messageHelper) {
		this.messageHelper = messageHelper;
	}

	/**
	 * generated when we found an unexpected exception in the code
	 * 
	 * @param cause
	 * @return
	 */
	public BaseException getUnexpectedException(Throwable cause){		
		return getException(cause, "exception.UnexpectedException", null);
	}

	public BaseException getJavaToJsonException(Throwable cause){		
		return getException(cause, "exception.JavaToJson", null);
	}
	
	public BaseException getJsonToJavaException(Throwable cause){		
		return getException(cause, "exception.JsonToJava", null);
	}		
	
	
	private BaseException getException(String errorCode,Object... messageParameters ){
		BaseException ex = new BaseException(errorCode, messageParameters);
		ex.setMessageHelper(messageHelper);
		return ex;
	}

	
	private BaseException getException(Throwable cause,String errorCode,Object... messageParameters ){
		BaseException ex = new BaseException(cause,errorCode, messageParameters);
		ex.setMessageHelper(messageHelper);
		return ex;
	}

			
}
