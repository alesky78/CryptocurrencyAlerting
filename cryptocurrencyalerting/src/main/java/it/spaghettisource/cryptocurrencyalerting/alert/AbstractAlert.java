package it.spaghettisource.cryptocurrencyalerting.alert;

import it.spaghettisource.cryptocurrencyalerting.provider.MarketAdapter;


/**
 * basic implementation of the Alert that implemented the common logic of cooldown and disable/enable mechanism
 * 
 * 
 * 
 * @author Alessandro D'Ottavio
 * @version 1.0
 */
public abstract class AbstractAlert implements Alert {

	protected boolean disable;
	protected boolean disableAfterTrigger;	
	protected boolean coolDown;	//is in coolDown mode
	protected boolean enableCoolDown; //is requested to go in coolDown after the trigger		
	protected long coolDownSeconds;	
	
	protected long lastTriggerSecond;
	protected long timePassSinceLastTriggerSecond;	
	
	protected MarketAdapter adapter;
	
	public AbstractAlert(MarketAdapter adapter) {
		super();
		disable = false;
		disableAfterTrigger = false;
		coolDown = true;
		coolDownSeconds = 900L; //15 minuts by default
		
		lastTriggerSecond=-1;
		
		this.adapter = adapter;
	}
	
	
	public void checkAlert() {
		
		if(!disable) {
			//check if in coolDown 
			if(enableCoolDown && coolDown) {
				
				timePassSinceLastTriggerSecond = (System.currentTimeMillis()/1000L) - lastTriggerSecond;
				
				if(timePassSinceLastTriggerSecond>coolDownSeconds) {
					coolDown = false;	//the coolDown period is finish
				}
				
			}else {
				
				boolean triggered = checkAndTrigger();
				
				lastTriggerSecond = System.currentTimeMillis()/1000L;
				
				if(enableCoolDown) {
					coolDown = true;
				}
				
				if(triggered && disableAfterTrigger) {
					disable = true;
				}				
			}
		}

	}
	

	@Override
	public boolean isDisable() {
		return disable;
	}

	
	@Override
	public void setDisable(boolean disable) {
		this.disable = disable;
	}


	@Override
	public void disableAfterTrigger(boolean status) {
		disableAfterTrigger = status;
	}

	@Override
	public void enableCoolDown(long seconds) {
		enableCoolDown = true;
		coolDownSeconds = seconds;
	}

	@Override
	public void disableCoolDown() {
		enableCoolDown = false;
		coolDownSeconds = -1;
	}
	
	/**
	 * 
	 * 
	 * @return true if the trigger is activated
	 */
	protected abstract boolean checkAndTrigger();

}
