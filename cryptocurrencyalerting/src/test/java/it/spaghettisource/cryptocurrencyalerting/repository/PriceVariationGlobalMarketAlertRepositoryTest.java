package it.spaghettisource.cryptocurrencyalerting.repository;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import it.spaghettisource.cryptocurrencyalerting.action.ActionType;
import it.spaghettisource.cryptocurrencyalerting.alert.PriceVariationGlobalMarketAlert;
import it.spaghettisource.cryptocurrencyalerting.exception.BaseException;
import it.spaghettisource.cryptocurrencyalerting.exception.ExceptionFactory;
import it.spaghettisource.cryptocurrencyalerting.i18n.MessageRepository;
import it.spaghettisource.cryptocurrencyalerting.i18n.StringMessageHelper;
import it.spaghettisource.cryptocurrencyalerting.utils.FileUtil;
import junit.framework.Assert;

public class PriceVariationGlobalMarketAlertRepositoryTest {

	ExceptionFactory exceptionFactory = new ExceptionFactory();
	PriceVariationGlobalMarketAlertRepository repository;
	
	String repositoryFilePath;
	String repositoryFileName;	
	
	
	@Before
	public void beforeTest() {
		
		//prepare the exception factory
		MessageRepository messageRepository =new MessageRepository();
		messageRepository.setMessageRepositoryBundleBaseName("it.spaghettisource.cryptocurrencyalerting.repository.PriceVariationGlobalMarketAlertRepositoryTest");
		StringMessageHelper helper = new StringMessageHelper();
		helper.setMessageRepository(messageRepository);
		exceptionFactory = new ExceptionFactory();
		exceptionFactory.setMessageHelper(helper);
		
		repository = new PriceVariationGlobalMarketAlertRepository(exceptionFactory);	
		
		//overwrite to put the data in the test resources
		//this is not needed in the code, the repository keep internally its configuration
		repositoryFilePath = System.getProperty("user.dir")+"/src/test/resources/it/spaghettisource/cryptocurrencyalerting/repository";
		repositoryFileName = "PriceVariationGlobalMarketAlertRepositoryTest.json";
				
		repository.setFilePath(repositoryFilePath);
		repository.setFileName(repositoryFileName);		
		
	}
	
	@After
	public void afterTest() {
		FileUtil.deleteFile(repositoryFilePath, repositoryFileName);
	}	
	
	
	@Test
	public void test_OK_SaveAndGetById() {
		
		repository.deleteAll();
		PriceVariationGlobalMarketAlert alert = repository.save(buildAlert(null));
		Assert.assertNotNull(repository.get(alert.getId()));
		
	}

	@Test(expected = BaseException.class)
	public void test_KO_DuplicatePK() {
		
		repository.deleteAll();
		PriceVariationGlobalMarketAlert alert = repository.save(buildAlert(null));
		repository.save(alert);		
	}	
	
	@Test
	public void test_OK_Update() {
		
		repository.deleteAll();
		PriceVariationGlobalMarketAlert alert = repository.save(buildAlert("id"));
		String criptocurrency= alert.getCriptocurency();
		
		alert.setCriptocurency("XRP");
		repository.update(alert);
		alert = repository.get(alert.getId());
		
		Assert.assertTrue(!criptocurrency.equals(alert.getCriptocurency()));		
	}		
	
	@Test(expected = BaseException.class)
	public void test_KO_EntityNotFound() {
		
		repository.deleteAll();
		repository.update(buildAlert(null));
	}		
	
	@Test
	public void test_OK_GetAll() {

		repository.deleteAll();
		repository.save(buildAlert(null));
		repository.save(buildAlert(null));
		repository.save(buildAlert(null));		
	
		Assert.assertEquals(3, repository.getAll().size());
		
	}

	@Test
	public void test_OK_Delete() {

		repository.deleteAll();
		PriceVariationGlobalMarketAlert id1 = repository.save(buildAlert(null));
		PriceVariationGlobalMarketAlert id2 = repository.save(buildAlert(null));
		PriceVariationGlobalMarketAlert id3 = repository.save(buildAlert(null));
	
		repository.delete(id1);
		repository.delete(id3);		
		
		
		Assert.assertEquals(1, repository.getAll().size());
		Assert.assertEquals(id2.getId(), repository.getAll().get(0).getId());		
		
	}
	
	private PriceVariationGlobalMarketAlert buildAlert(String id) {
		PriceVariationGlobalMarketAlert alert = new PriceVariationGlobalMarketAlert();
		alert.setActionType(ActionType.SmtpMailAction);
		alert.setCriptocurency("BTC");
		alert.setFiat("EUR");
		alert.setPrice(5006.8967);		
		alert.setMode(PriceVariationGlobalMarketAlert.ABOVE);

		return alert;
	}
	
	
}
