package it.spaghettisource.cryptocurrencyalerting.repository;

import it.spaghettisource.cryptocurrencyalerting.action.SmtpMailAction;
import it.spaghettisource.cryptocurrencyalerting.exception.ExceptionFactory;

/**
 * repository for the {@link SmtpMailAction}
 *
 * @author D'Ottavio Alessandro
 * @version 1.0
 */
public class SmtpMailActionRepository extends CommonRepository<SmtpMailAction> {

	
	public SmtpMailActionRepository(ExceptionFactory exceptionFactory) {
		super(exceptionFactory);
		filePath = System.getProperty("user.dir")+ System.getProperty("file.separator")+ "repository";
		fileName = "smtpMailAction.json";

	}

	
}
