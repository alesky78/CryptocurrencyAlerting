package it.spaghettisource.cryptocurrencyalerting.services;

import it.spaghettisource.cryptocurrencyalerting.action.Action;
import it.spaghettisource.cryptocurrencyalerting.action.ActionType;
import it.spaghettisource.cryptocurrencyalerting.action.SmtpMailAction;
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
	
	private ExceptionFactory exceptionFactory;
	
	public ActionService(ExceptionFactory exceptionFactory) {
		super();
		this.exceptionFactory = exceptionFactory;
	}
	
	
	
	public Action findAction(ActionType actionType) {
		
		Action action = null;
		
		if(actionType.equals(ActionType.SmtpMailAction)) {
			action = new SmtpMailAction(exceptionFactory);
		}
		
		return action;
	}
	
	

	
}
