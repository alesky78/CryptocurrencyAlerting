package it.spaghettisource.cryptocurrencyalerting.services;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import it.spaghettisource.cryptocurrencyalerting.alert.Alert;
import it.spaghettisource.cryptocurrencyalerting.alert.AlertType;
import it.spaghettisource.cryptocurrencyalerting.alert.PriceVariationGlobalMarketAlert;
import it.spaghettisource.cryptocurrencyalerting.exception.BaseException;
import it.spaghettisource.cryptocurrencyalerting.exception.ExceptionFactory;
import it.spaghettisource.cryptocurrencyalerting.i18n.MessageRepository;
import it.spaghettisource.cryptocurrencyalerting.i18n.StringMessageHelper;
import it.spaghettisource.cryptocurrencyalerting.repository.PriceVariationGlobalMarketAlertRepository;
import it.spaghettisource.cryptocurrencyalerting.utils.FileUtil;

public class AlertServiceTest {

	AlertService alertService;
	PriceVariationGlobalMarketAlertRepository priceVariationGlobalMarketAlertRepository;
	
	String repositoryFilePath;
	String repositoryFileName;
	
	@Before
	public void beforeTest() {
		
		//prepare the exception factory
		MessageRepository messageRepository =new MessageRepository();
		messageRepository.setMessageRepositoryBundleBaseName("it.spaghettisource.cryptocurrencyalerting.services.AlertServiceTest");
		StringMessageHelper helper = new StringMessageHelper();
		helper.setMessageRepository(messageRepository);
		ExceptionFactory exceptionFactory = new ExceptionFactory();
		exceptionFactory.setMessageHelper(helper);
		
		priceVariationGlobalMarketAlertRepository = new PriceVariationGlobalMarketAlertRepository(exceptionFactory);	
		
		//overwrite to put the data in the test resources
		//this is not needed in the code, the repository keep internally its configuration
		repositoryFilePath = System.getProperty("user.dir")+"\\src\\test\\resources\\it\\spaghettisource\\cryptocurrencyalerting\\services";
		repositoryFileName = "alertServiceTest_priceVariationGlobalMarketAlertRepository.json";
				
		priceVariationGlobalMarketAlertRepository.setFilePath(repositoryFilePath);
		priceVariationGlobalMarketAlertRepository.setFileName(repositoryFileName);	
		
		alertService = new AlertService(exceptionFactory, priceVariationGlobalMarketAlertRepository);
				
	}
	
	
	@After
	public void afterTest() {
		FileUtil.deleteFile(repositoryFilePath, repositoryFileName);
	}		
	
	
	@Test
	public void test_OK_SaveNewAlert() {
		
		priceVariationGlobalMarketAlertRepository.deleteAll();
		alertService.saveNewAlert(buildPriceVariationGlobalMarketAlert());
		priceVariationGlobalMarketAlertRepository.deleteAll();		
		Assert.assertTrue(true);
	}

	
	@Test
	public void test_OK_UpdateAlert() {
		
		priceVariationGlobalMarketAlertRepository.deleteAll();
		PriceVariationGlobalMarketAlert alert = alertService.saveNewAlert(buildPriceVariationGlobalMarketAlert());
		alert.setCriptocurency("XRP");
		alertService.updateAlert(alert);
		
		String updateCriptocurency = priceVariationGlobalMarketAlertRepository.get(alert.getId()).getCriptocurency();
		
		priceVariationGlobalMarketAlertRepository.deleteAll();		
		
		Assert.assertEquals("XRP", updateCriptocurency);
	}
	

	@Test
	public void test_OK_FindAlert()  throws BaseException {
		
		priceVariationGlobalMarketAlertRepository.deleteAll();
		PriceVariationGlobalMarketAlert alert = alertService.saveNewAlert(buildPriceVariationGlobalMarketAlert());

		alert = (PriceVariationGlobalMarketAlert) alertService.findAlert(AlertType.PriceVariationGlobalMarketAlert, alert.getId());
		Assert.assertNotNull(alert);
		
	}	
	
	
	@Test
	public void test_OK_FindAllAlert()  throws BaseException {
		
		priceVariationGlobalMarketAlertRepository.deleteAll();
		alertService.saveNewAlert(buildPriceVariationGlobalMarketAlert());
		alertService.saveNewAlert(buildPriceVariationGlobalMarketAlert());
		alertService.saveNewAlert(buildPriceVariationGlobalMarketAlert());

		List<Alert> actionsId = alertService.findAllAlert() ;
		priceVariationGlobalMarketAlertRepository.deleteAll();		
		
		Assert.assertTrue(actionsId.size()==3);
		
	}		

	
	private PriceVariationGlobalMarketAlert buildPriceVariationGlobalMarketAlert() {
		PriceVariationGlobalMarketAlert alert = new PriceVariationGlobalMarketAlert("BTC", "EUR", 5000.20d, PriceVariationGlobalMarketAlert.ABOVE);
		return alert;
	}
	
	
}
