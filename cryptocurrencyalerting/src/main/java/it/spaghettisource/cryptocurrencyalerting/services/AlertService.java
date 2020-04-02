package it.spaghettisource.cryptocurrencyalerting.services;

import java.util.ArrayList;
import java.util.List;

import it.spaghettisource.cryptocurrencyalerting.action.Action;
import it.spaghettisource.cryptocurrencyalerting.action.ActionType;
import it.spaghettisource.cryptocurrencyalerting.alert.Alert;
import it.spaghettisource.cryptocurrencyalerting.alert.AlertType;
import it.spaghettisource.cryptocurrencyalerting.alert.PriceVariationGlobalMarketAlert;
import it.spaghettisource.cryptocurrencyalerting.exception.BaseException;
import it.spaghettisource.cryptocurrencyalerting.exception.ExceptionFactory;
import it.spaghettisource.cryptocurrencyalerting.repository.PriceVariationGlobalMarketAlertRepository;

public class AlertService {

	private ExceptionFactory exceptionFactory;
	private PriceVariationGlobalMarketAlertRepository priceVariationGlobalMarketAlertRepository;

	public AlertService(ExceptionFactory exceptionFactory) {
		this.exceptionFactory = exceptionFactory;
		priceVariationGlobalMarketAlertRepository = new PriceVariationGlobalMarketAlertRepository(exceptionFactory);
	}
	
	public AlertService(ExceptionFactory exceptionFactory,PriceVariationGlobalMarketAlertRepository priceVariationGlobalMarketAlertRepository) {
		super();
		this.exceptionFactory = exceptionFactory;
		this.priceVariationGlobalMarketAlertRepository = priceVariationGlobalMarketAlertRepository;
	}


	public PriceVariationGlobalMarketAlert saveNewAlert(PriceVariationGlobalMarketAlert alert)  throws BaseException {
		return priceVariationGlobalMarketAlertRepository.save(alert);
	}

	
	public void updateAlert(PriceVariationGlobalMarketAlert alert)  throws BaseException {
		priceVariationGlobalMarketAlertRepository.update(alert);
	}	

	
	public Alert findAlert(AlertType actionType,String id){
		
		Alert alert = null;
		
		if(actionType.equals(AlertType.PriceVariationGlobalMarketAlert)) {
			alert = priceVariationGlobalMarketAlertRepository.get(id);
		}
		
		return alert;
		
	}
	
	public List<Alert> findAllAlert(){
		List<Alert> alerts = new ArrayList<>();
		
		alerts.addAll(priceVariationGlobalMarketAlertRepository.getAll());
		
		return alerts;
		
	}




}
