package it.spaghettisource.cryptocurrencyalerting.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.spaghettisource.cryptocurrencyalerting.action.Action;
import it.spaghettisource.cryptocurrencyalerting.action.ActionType;
import it.spaghettisource.cryptocurrencyalerting.action.PopupAction;
import it.spaghettisource.cryptocurrencyalerting.action.SmtpMailAction;
import it.spaghettisource.cryptocurrencyalerting.exception.BaseException;
import it.spaghettisource.cryptocurrencyalerting.exception.ExceptionFactory;

/**
 * This class has the responsibilities to manage all the action services requested
 * avoiding to have this logic distributed, this will allow in the future to migrate to a more appropriate level of configuration in the actions
 * 
 * i will try to rest more sticky to the target interfaces in the way to semplify the migration later
 * 
 * @author Alessandro D'Ottavio
 * @version 1.0
 */
public class ActionService {
	
	static Logger log = LoggerFactory.getLogger(ActionService.class);
	
	private ExceptionFactory exceptionFactory;
	
	public ActionService(ExceptionFactory exceptionFactory) {
		super();
		this.exceptionFactory = exceptionFactory;
	}
	
	
	
	public Action findAction(ActionType actionType) {
		
		
		if(actionType.equals(ActionType.SmtpMailAction)) {
			return new SmtpMailAction(exceptionFactory);
		}else if(actionType.equals(ActionType.PopupAction)) {
			return new PopupAction(exceptionFactory);
		}
		
		BaseException exception = exceptionFactory.getActionNotExsist(actionType);
		log.error(exception.getMessage(),exception);
		throw exception;
		
	}
	
	

	
}
