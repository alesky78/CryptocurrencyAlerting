package it.spaghettisource.cryptocurrencyalerting.action;

/**
 * Base Action implementation
 * 
 * @author Alessandro D'Ottavio
 * @version 1.0
 */
public abstract class AbstractAction  implements Action {
	
	
	protected ActionType actionType;

	
	public ActionType getActionType() {
		return actionType;
	}

	public void setActionType(ActionType actionType) {
		this.actionType = actionType;
	}
	
	
	
}
