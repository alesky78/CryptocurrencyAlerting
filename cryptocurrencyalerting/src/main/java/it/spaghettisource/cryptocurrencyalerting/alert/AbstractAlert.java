package it.spaghettisource.cryptocurrencyalerting.alert;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.spaghettisource.cryptocurrencyalerting.action.Action;
import it.spaghettisource.cryptocurrencyalerting.action.ActionType;
import it.spaghettisource.cryptocurrencyalerting.repository.CommonEntity;
import it.spaghettisource.cryptocurrencyalerting.services.ServiceLocator;


/**
 * basic implementation of the Alert that implemented the common logic of cooldown and disable/enable mechanism
 * 
 * 
 * 
 * @author Alessandro D'Ottavio
 * @version 1.0
 */
public abstract class AbstractAlert extends CommonEntity implements Alert {

	static Logger log = LoggerFactory.getLogger(AbstractAlert.class);
	
	protected AlertType alertType;
	
	protected boolean disable;
	protected boolean disableAfterTrigger;	
	protected boolean coolDown;	//is in coolDown mode
	protected boolean enableCoolDown; //is requested to go in coolDown after the trigger		
	protected long coolDownMinuts;	
	
	protected long lastTrigger;
	protected long timePassSinceLastTrigger;	
	
	protected ActionType actionType;
	
	public AbstractAlert() {
		super();
		disable = false;
		disableAfterTrigger = false;
		coolDown = false;
		enableCoolDown = true;
		coolDownMinuts = 60L; //60 minuts by default		
		lastTrigger=-1;
		timePassSinceLastTrigger=-1;
	}
	
	



	public void checkAlert() {
		
		if(!disable) {
			//check if in coolDown 
			if(enableCoolDown && coolDown) {
								
				timePassSinceLastTrigger = System.currentTimeMillis() - lastTrigger;
				
				if(timePassSinceLastTrigger > (coolDownMinuts*60*1000) ) {
					coolDown = false;	//the coolDown period is finish
					log.debug("alert-"+id+"coolDown mode completed");
				}else {
					log.debug("alert-"+id+" in collDown mode");
				}
				
			}else {
				
				if(checkAndTrigger()) {
					lastTrigger = System.currentTimeMillis();
					
					if(enableCoolDown) {
						log.debug("alert-"+id+"activate coolDown after trigger");
						coolDown = true;

					}
					
					if(disableAfterTrigger) {
						disable = true;
						log.debug("alert-"+id+"disable after trigger");
					}
				}
				
			}
			
			storeUpdatedData();
			
		}else {
			log.debug("alert-"+id+"alert disable");
		}

	}
	
	
	

	private long getCurrentTimeInmiuntes() {
		return System.currentTimeMillis()/1000L/60L;
	}
	
	protected Action getAction() {
		return  ServiceLocator.getInstance().getActionService().findAction(actionType);
	}

	
	@Override
	public void disableAfterTrigger(boolean status) {
		disableAfterTrigger = status;
	}

	@Override
	public void enableCoolDown(long minuts) {
		enableCoolDown = true;
		coolDownMinuts = minuts;
	}

	@Override
	public void disableCoolDown() {
		enableCoolDown = false;
		coolDownMinuts = -1;
	}
		
	public boolean isDisableAfterTrigger() {
		return disableAfterTrigger;
	}


	public void setDisableAfterTrigger(boolean disableAfterTrigger) {
		this.disableAfterTrigger = disableAfterTrigger;
	}

	@Override
	public boolean isDisable() {
		return disable;
	}
	@Override
	public void setDisable(boolean disable) {
		this.disable = disable;
	}


	public boolean isCoolDown() {
		return coolDown;
	}


	public void setCoolDown(boolean coolDown) {
		this.coolDown = coolDown;
	}


	public boolean isEnableCoolDown() {
		return enableCoolDown;
	}


	public void setEnableCoolDown(boolean enableCoolDown) {
		this.enableCoolDown = enableCoolDown;
	}

	public long getCoolDownMinuts() {
		return coolDownMinuts;
	}


	public void setCoolDownMinuts(long coolDownMinuts) {
		this.coolDownMinuts = coolDownMinuts;
	}


	public long getLastTriggerSecond() {
		return lastTrigger;
	}


	public void setLastTriggerSecond(long lastTriggerSecond) {
		this.lastTrigger = lastTriggerSecond;
	}


	public long getTimePassSinceLastTriggerSecond() {
		return timePassSinceLastTrigger;
	}


	public void setTimePassSinceLastTriggerSecond(long timePassSinceLastTriggerSecond) {
		this.timePassSinceLastTrigger = timePassSinceLastTriggerSecond;
	}

	public ActionType getActionType() {
		return actionType;
	}


	public void setActionType(ActionType actionType) {
		this.actionType = actionType;
	}


	public AlertType getAlertType() {
		return alertType;
	}


	public void setAlertType(AlertType alertType) {
		this.alertType = alertType;
	}


	/**
	 * 
	 * 
	 * @return true if the trigger is activated
	 */
	protected abstract boolean checkAndTrigger();
	
	
	/**
	 * The {@link#Agent} get alwasy the data from the repository and don't use the data in memory
	 * so every time the propreties are changed, the data must be store again in the file system, to be sure that the Agent will take alwasy the last version
	 * but ot is responsibility of the specific alert know what is its repository where to store itself
	 */
	protected abstract void storeUpdatedData();
		

}
