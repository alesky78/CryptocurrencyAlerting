package it.spaghettisource.cryptocurrencyalerting.exception;


import it.spaghettisource.cryptocurrencyalerting.action.ActionType;
import it.spaghettisource.cryptocurrencyalerting.i18n.I18NMessageHelper;


/**
 * Factory class used to create exception, this class allow to format the correct error message and hide the error code 
 * avoiding to put the error code in the business code
 * 
 * @author Alessando D'Ottavio
 * @version 1.0
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
	
	///////////REPOSITORY//////////////////
	public BaseException getDuplicatePrimariKey(Object pk) {
		return getException("exception.repostiory.duplicatePrimaryKey",  new Object[]{pk});
	}

	public BaseException getEntityNotExsist() {
		return getException("exception.repostiory.entityNotExsist", EMPTY_PARAMETERS);
	}


	///////////SERVICES//////////////////
	public BaseException getActionNotExsist(ActionType actionType) {
		return getException("exception.action.notexist",  new Object[]{actionType});
	}

	
	///////////HTTP ERRORS/////////////////
	public BaseException getSSLProtocolNotSupported(Exception cause) {
		return getException(cause, "exception.http.ssl.notsupported", EMPTY_PARAMETERS);
	}
	public BaseException getHttp400() {
		return getException("exception.http.code.400", EMPTY_PARAMETERS);
	}	
	public BaseException getInvalidURL(Exception cause,String url) {
		return getException(cause,"exception.http.invalid.url", new Object[]{url});
	}	
	
	
	/////////COIN MARKET CAP ERRORS////////////////
	public BaseException getCoinMarketCapHttp400() {
		return getException("exception.http.coinmarketcap.code.400", EMPTY_PARAMETERS);
	}	
	public BaseException getCoinMarketCapHttp401() {
		return getException("exception.http.coinmarketcap.code.401", EMPTY_PARAMETERS);
	}	
	public BaseException getCoinMarketCapHttp402() {
		return getException("exception.http.coinmarketcap.code.402", EMPTY_PARAMETERS);
	}	
	public BaseException getCoinMarketCapHttp403() {
		return getException("exception.http.coinmarketcap.code.403", EMPTY_PARAMETERS);
	}	
	public BaseException getCoinMarketCapHttp429() {
		return getException("exception.http.coinmarketcap.code.429", EMPTY_PARAMETERS);
	}	
	public BaseException getCoinMarketCapHttp500() {
		return getException("exception.http.coinmarketcap.code.500", EMPTY_PARAMETERS);
	}	
	
	/////////COIN MARKET CAP ERRORS////////////////
	public BaseException getAlternativeMeHttpError(int httpCode) {
		return getException("exception.http.alternativeme.code.any",  new Object[]{httpCode});
	}	
	
	///////////internal methods/////////////////
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
