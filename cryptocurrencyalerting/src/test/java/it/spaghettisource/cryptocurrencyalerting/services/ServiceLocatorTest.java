package it.spaghettisource.cryptocurrencyalerting.services;

import org.junit.Test;

import it.spaghettisource.cryptocurrencyalerting.services.ServiceLocator;

public class ServiceLocatorTest {

	@Test
	public void test_OK_getInsance() {
		
		ServiceLocator.getInstance();
		
	}
		
}
