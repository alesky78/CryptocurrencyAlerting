package it.spaghettisource.cryptocurrencyalerting.repository;

import it.spaghettisource.cryptocurrencyalerting.exception.ExceptionFactory;

/**
 * <code>CommonRepository</code> implementation of the repository that relay on all the entity class that extends {@link CommonEntity}
 *
 * 
 * @author Alessandro D'Ottavio
 * @version 1.0
 *
 */
public class CommonRepository<T extends Entity<String>> extends GenericFileRepository<T, String> {

	public CommonRepository(ExceptionFactory exceptionFactory) {
		this.exceptionFactory = exceptionFactory;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}	
	
	@Override
	public String generateKey() {
		return  Long.toString(System.currentTimeMillis());
	}
	

}
