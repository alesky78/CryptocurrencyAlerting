package it.spaghettisource.cryptocurrencyalerting.action;

/**
 * Base interface for all the possible actions
 * 
 * @author Alessandro
 * @version 1.0
 */
public interface Action {

	/**
	 * execute this action
	 */
	public void trigger(String message);
	
	/**
	 * 
	 * @return the action type
	 */
	public ActionType getActionType();
	
}
