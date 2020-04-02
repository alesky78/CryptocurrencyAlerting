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
	protected long coolDownSeconds;	
	
	protected long lastTriggerSecond;
	protected long timePassSinceLastTriggerSecond;	
	
	@JsonIgnore
	protected MarketAdapter adapter;
	@JsonIgnore
	protected ActionService actionManager;
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
		coolDownSeconds = 900L; //15 minuts by default		
		lastTriggerSecond=-1;
		timePassSinceLastTriggerSecond=-1;
	}
	
	
	public void setAdapter(MarketAdapter adapter) {
		this.adapter = adapter;
	}

	public void setActionManager(ActionService actionManager) {
		this.actionManager = actionManager;
	}
	
	public void setMessageHelper(StringMessageHelper messageHelper) {
		this.messageHelper = messageHelper;
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
	
	
	protected Action getAction() {
		return actionManager.findAction(actionType, actionId);
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
		
	public boolean isDisableAfterTrigger() {
		return disableAfterTrigger;
	}


	public void setDisableAfterTrigger(boolean disableAfterTrigger) {
		this.disableAfterTrigger = disableAfterTrigger;
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


	public long getCoolDownSeconds() {
		return coolDownSeconds;
	}


	public void setCoolDownSeconds(long coolDownSeconds) {
		this.coolDownSeconds = coolDownSeconds;
	}


	public long getLastTriggerSecond() {
		return lastTriggerSecond;
	}


	public void setLastTriggerSecond(long lastTriggerSecond) {
		this.lastTriggerSecond = lastTriggerSecond;
	}


	public long getTimePassSinceLastTriggerSecond() {
		return timePassSinceLastTriggerSecond;
	}


	public void setTimePassSinceLastTriggerSecond(long timePassSinceLastTriggerSecond) {
		this.timePassSinceLastTriggerSecond = timePassSinceLastTriggerSecond;
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
