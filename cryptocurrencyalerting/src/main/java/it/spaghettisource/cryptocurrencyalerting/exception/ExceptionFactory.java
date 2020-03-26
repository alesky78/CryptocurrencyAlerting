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

	private static Object[] EMPTY_PARAMETERS = new Object[] {};
	
	private I18NMessageHelper messageHelper;
	
	public void setMessageHelper(I18NMessageHelper messageHelper) {
		this.messageHelper = messageHelper;
	}

	public BaseException getUnexpectedException(Throwable cause){		
		return getException(cause, "exception.UnexpectedException", EMPTY_PARAMETERS);
	}

	public BaseException getJavaToJsonException(Throwable cause){		
		return getException(cause, "exception.JavaToJson", EMPTY_PARAMETERS);
	}
	
	public BaseException getJsonToJavaException(Throwable cause){		
		return getException(cause, "exception.JsonToJava", EMPTY_PARAMETERS);
	}		

	public BaseException getImpossibleReadFileException(Exception cause, String fileName,String filePath) {
		return getException(cause, "exception.readFile", new Object[]{fileName, filePath});
	}
	
	public BaseException getImpossibleWriteFileException(Exception cause, String fileName,String filePath) {
		return getException(cause, "exception.writeFile", new Object[]{fileName, filePath});
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
