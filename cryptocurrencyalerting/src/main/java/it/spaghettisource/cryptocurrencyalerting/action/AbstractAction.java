package it.spaghettisource.cryptocurrencyalerting.action;

import it.spaghettisource.cryptocurrencyalerting.repository.CommonEntity;

/**
 * Base Action implementation
 * 
 * @author Alessandro D'Ottavio
 * @version 1.0
 */
public abstract class AbstractAction extends CommonEntity implements Action {
	
	
	protected ActionType actionType;

	
	public ActionType getActionType() {
		return actionType;
	}

	public void setActionType(ActionType actionType) {
		this.actionType = actionType;
	}
	
	
	
}
