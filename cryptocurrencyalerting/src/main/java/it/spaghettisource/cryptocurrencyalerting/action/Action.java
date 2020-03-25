package it.spaghettisource.cryptocurrencyalerting.action;

/**
 * Base interface for all the possible actions
 * 
 * @author Alessandro
 *
 */
public interface Action {

	/**
	 * execute this action
	 */
	public void trigger();
	
	
}
