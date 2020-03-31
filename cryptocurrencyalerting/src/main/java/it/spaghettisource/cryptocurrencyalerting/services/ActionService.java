package it.spaghettisource.cryptocurrencyalerting.services;

import java.util.ArrayList;
import java.util.List;

import it.spaghettisource.cryptocurrencyalerting.action.Action;
import it.spaghettisource.cryptocurrencyalerting.action.SmtpMailAction;
import it.spaghettisource.cryptocurrencyalerting.exception.BaseException;
import it.spaghettisource.cryptocurrencyalerting.exception.ExceptionFactory;
import it.spaghettisource.cryptocurrencyalerting.repository.SmtpMailActionRepository;

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

	public static String ACTION_TYPE_SMTP_MAIL = "action.type.smtpmail";	
	
	private ExceptionFactory exceptionFactory;
	
	public ActionService(ExceptionFactory exceptionFactory) {
		this.exceptionFactory = exceptionFactory;
	}
	
	
	public List<String> getActionName(String actionType) {
		
		List<String> names = new ArrayList<String>();
		
		if(actionType.equals(ACTION_TYPE_SMTP_MAIL)) {
			SmtpMailActionRepository repository = new SmtpMailActionRepository(exceptionFactory) ;
			 List<SmtpMailAction> mailsAction = repository.getAll();
			 for (SmtpMailAction smtpMailAction : mailsAction) {
				 names.add(smtpMailAction.getName());
			}	
		}
		
		return names;
	}
	
	
	public Action getAction(String actionType,String name) {
		
		Action action = null;
		
		if(actionType.equals(ACTION_TYPE_SMTP_MAIL)) {
			SmtpMailActionRepository repository = new SmtpMailActionRepository(exceptionFactory) ;
			 List<SmtpMailAction> mailsAction = repository.getAll();
			 for (SmtpMailAction smtpMailAction : mailsAction) {
				 if(smtpMailAction.getName().equals(name)) {
					 action = smtpMailAction;
				 }
			}	
		}
		
		return action;
	}
	
	
	public void saveNewAction(SmtpMailAction action)  throws BaseException {
		SmtpMailActionRepository repository = new SmtpMailActionRepository(exceptionFactory) ;
		repository.save(action);
	}

	public void updateAction(SmtpMailAction action)  throws BaseException {
		SmtpMailActionRepository repository = new SmtpMailActionRepository(exceptionFactory) ;
		repository.update(action);
	}
	
}
