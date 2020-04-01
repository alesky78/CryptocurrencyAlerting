package it.spaghettisource.cryptocurrencyalerting.repository;

import it.spaghettisource.cryptocurrencyalerting.alert.PriceVariationGlobalMarketAlert;
import it.spaghettisource.cryptocurrencyalerting.exception.ExceptionFactory;

/**
 * repository for the {@link PriceVariationGlobalMarketAlert}
 *
 * @author D'Ottavio Alessandro
 * @version 1.0
 */
public class PriceVariationGlobalMarketAlertRepository extends CommonRepository<PriceVariationGlobalMarketAlert> {

	
	public PriceVariationGlobalMarketAlertRepository(ExceptionFactory exceptionFactory) {
		super(exceptionFactory);
		filePath = System.getProperty("user.dir")+ System.getProperty("file.separator")+ "repository";
		fileName = "PriceVariationGlobalMarketAlert.json";
	}

	
}
