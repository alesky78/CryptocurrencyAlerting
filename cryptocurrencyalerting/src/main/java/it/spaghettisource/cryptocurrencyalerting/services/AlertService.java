package it.spaghettisource.cryptocurrencyalerting.services;

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

	public void saveNewAlert(PriceVariationGlobalMarketAlert alert)  throws BaseException {
		priceVariationGlobalMarketAlertRepository.save(alert);
	}

	public void updateAlert(PriceVariationGlobalMarketAlert alert)  throws BaseException {
		priceVariationGlobalMarketAlertRepository.update(alert);
	}	

	public void getAllAlert(){

	}




}
