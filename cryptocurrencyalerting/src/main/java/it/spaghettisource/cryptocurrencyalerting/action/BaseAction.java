package it.spaghettisource.cryptocurrencyalerting.action;

import it.spaghettisource.cryptocurrencyalerting.repository.CommonEntity;

/**
 * Base Action implementation
 * 
 * @author Alessandro D'Ottavio
 * @version 1.0
 */
public abstract class BaseAction extends CommonEntity implements Action {

	protected String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
