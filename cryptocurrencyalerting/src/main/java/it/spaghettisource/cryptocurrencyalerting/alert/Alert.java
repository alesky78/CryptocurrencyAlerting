package it.spaghettisource.cryptocurrencyalerting.alert;

/**
 * Base interface for all the possible alerts
 * 
 * @author Alessandro
 * @version 1.0
 */
public interface Alert {

	
	
	/**
	 * verify if this alert is triggered
	 * 
	 */
	public void checkAlert();
	
	
	/**
	 * is the alert disable
	 * 
	 * @return the status of the alert
	 */
	public boolean isDisable();
	

	/**
	 * disable this alert
	 * 
	 * @return the status of the alert
	 */
	public void setDisable(boolean disable);
	
	
	/**
	 * configure to disable or not after a trigger
	 * 
	 * @return the status of the alert
	 */
	public void disableAfterTrigger(boolean status);
	
	
	/**
	 * is the alert disable
	 * 
	 * @return the status of the alert
	 */
	public void enableCoolDown(long seconds);
	

	/**
	 * is the alert disable
	 * 
	 * @return the status of the alert
	 */
	public void disableCoolDown();
	
	
}
