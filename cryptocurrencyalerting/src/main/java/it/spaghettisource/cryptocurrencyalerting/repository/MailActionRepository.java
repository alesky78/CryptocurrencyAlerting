package it.spaghettisource.cryptocurrencyalerting.repository;

import it.spaghettisource.cryptocurrencyalerting.action.MailAction;
import it.spaghettisource.cryptocurrencyalerting.exception.ExceptionFactory;

/**
 * repository for the {@link MailAction}
 *
 * @author D'Ottavio Alessandro
 * @version 1.0
 */
public class MailActionRepository extends CommonRepository<MailAction> {

	
	public MailActionRepository(ExceptionFactory exceptionFactory) {
		super(exceptionFactory);
		filePath = System.getProperty("user.dir")+ System.getProperty("file.separator")+ "repository";
		fileName = "mailAction.json";

	}

	
}
