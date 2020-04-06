package it.spaghettisource.cryptocurrencyalerting.alert;

import com.fasterxml.jackson.annotation.JsonIgnore;

import it.spaghettisource.cryptocurrencyalerting.action.Action;
import it.spaghettisource.cryptocurrencyalerting.action.ActionType;
import it.spaghettisource.cryptocurrencyalerting.i18n.StringMessageHelper;
import it.spaghettisource.cryptocurrencyalerting.provider.MarketAdapter;
import it.spaghettisource.cryptocurrencyalerting.repository.CommonEntity;
import it.spaghettisource.cryptocurrencyalerting.services.ActionService;


/**
 * basic implementation of the Alert that implemented the common logic of cooldown and disable/enable mechanism
 * 
 * 
 * 
 * @author Alessandro D'Ottavio
 * @version 1.0
 */
public abstract class AbstractAlert extends CommonEntity implements Alert {

	protected AlertType alertType;
	
	protected boolean disable;
	protected boolean disableAfterTrigger;	
	protected boolean coolDown;	//is in coolDown mode
	protected boolean enableCoolDown; //is requested to go in coolDown after the trigger		
	protected long coolDownMinuts;	
	
	protected long lastTriggerMinutes;
	protected long timePassSinceLastTriggerMinutes;	
	
	@JsonIgnore
	protected MarketAdapter adapter;
	@JsonIgnore
	protected ActionService actionService;
	@JsonIgnore	
	protected StringMessageHelper messageHelper;
	
	protected ActionType actionType;
	protected String actionId;	
	
	public AbstractAlert() {
		super();
		disable = false;
		disableAfterTrigger = false;
		coolDown = false;
		enableCoolDown = true;
		coolDownMinuts = 60L; //60 minuts by default		
		lastTriggerMinutes=-1;
		timePassSinceLastTriggerMinutes=-1;
	}
	
	



	public void checkAlert() {
		
		if(!disable) {
			//check if in coolDown 
			if(enableCoolDown && coolDown) {
				
				timePassSinceLastTriggerMinutes = ((System.currentTimeMillis()/1000L)/60L) - lastTriggerMinutes;
				
				if(timePassSinceLastTriggerMinutes>coolDownMinuts) {
					coolDown = false;	//the coolDown period is finish
				}
				
			}else {
				
				boolean triggered = checkAndTrigger();
				
				lastTriggerMinutes = ((System.currentTimeMillis()/1000L)/60L);
				
				if(enableCoolDown) {
					coolDown = true;
				}
				
				if(triggered && disableAfterTrigger) {
					disable = true;
				}				
			}
		}

	}
	
	
	protected Action getAction() {
		return actionService.findAction(actionType, actionId);
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
		return lastTriggerMinutes;
	}


	public void setLastTriggerSecond(long lastTriggerSecond) {
		this.lastTriggerMinutes = lastTriggerSecond;
	}


	public long getTimePassSinceLastTriggerSecond() {
		return timePassSinceLastTriggerMinutes;
	}


	public void setTimePassSinceLastTriggerSecond(long timePassSinceLastTriggerSecond) {
		this.timePassSinceLastTriggerMinutes = timePassSinceLastTriggerSecond;
	}

	public ActionType getActionType() {
		return actionType;
	}


	public void setActionType(ActionType actionType) {
		this.actionType = actionType;
	}


	public String getActionId() {
		return actionId;
	}


	public void setActionId(String actionName) {
		this.actionId = actionName;
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

}
