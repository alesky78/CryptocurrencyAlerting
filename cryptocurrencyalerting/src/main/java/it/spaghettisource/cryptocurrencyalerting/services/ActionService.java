package it.spaghettisource.cryptocurrencyalerting.services;

import java.util.ArrayList;
import java.util.List;

import it.spaghettisource.cryptocurrencyalerting.action.Action;
import it.spaghettisource.cryptocurrencyalerting.action.ActionType;
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
	
	private ExceptionFactory exceptionFactory;
	private SmtpMailActionRepository repository;
	
	public ActionService(ExceptionFactory exceptionFactory) {
		super();
		this.exceptionFactory = exceptionFactory;
		repository = new SmtpMailActionRepository(exceptionFactory);
	}
	
	public ActionService(ExceptionFactory exceptionFactory, SmtpMailActionRepository repository) {
		super();
		this.exceptionFactory = exceptionFactory;
		this.repository = repository;
	}

	
	public List<String> findAllActionId(ActionType actionType) {
		
		List<String> id = new ArrayList<String>();
		
		if(actionType.equals(ActionType.SmtpMailAction)) {
			 List<SmtpMailAction> mailsAction = repository.getAll();
			 for (SmtpMailAction smtpMailAction : mailsAction) {
				 id.add(smtpMailAction.getId());
			}	
		}
		
		return id;
	}
	
	
	public Action findAction(ActionType actionType,String id) {
		
		Action action = null;
		
		if(actionType.equals(ActionType.SmtpMailAction)) {
			action = repository.get(id);
		}
		
		return action;
	}
	
	
	public void saveNewAction(SmtpMailAction action)  throws BaseException {
		repository.save(action);
	}

	public void updateAction(SmtpMailAction action)  throws BaseException {
		repository.update(action);
	}
	
}
