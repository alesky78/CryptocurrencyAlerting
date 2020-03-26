package it.spaghettisource.cryptocurrencyalerting.repository;

import org.junit.Before;
import org.junit.Test;

import it.spaghettisource.cryptocurrencyalerting.action.MailAction;
import it.spaghettisource.cryptocurrencyalerting.action.MailAction.EncryptType;
import it.spaghettisource.cryptocurrencyalerting.exception.ExceptionFactory;
import it.spaghettisource.cryptocurrencyalerting.i18n.MessageRepository;
import it.spaghettisource.cryptocurrencyalerting.i18n.StringMessageHelper;
import junit.framework.Assert;

public class MailActionRepositoryTest {

	ExceptionFactory exceptionFactory = new ExceptionFactory();
	MailActionRepository repository;
	
	@Before
	public void beforeTest() {
		
		//prepare the exception factory
		MessageRepository messageRepository =new MessageRepository();
		messageRepository.setMessageRepositoryBundleBaseName("it.spaghettisource.cryptocurrencyalerting.repository.MailActionRepositoryTest");
		StringMessageHelper helper = new StringMessageHelper();
		helper.setMessageRepository(messageRepository);
		exceptionFactory = new ExceptionFactory();
		exceptionFactory.setMessageHelper(helper);
		
		repository = new MailActionRepository(exceptionFactory);	
		
		//overwrite to put the data in the test resources\
		//this is not needed in the code, the repository keep internally its configuration
		repository.setFilePath(System.getProperty("user.dir")+"\\src\\test\\resources\\it\\spaghettisource\\cryptocurrencyalerting\\repository");
		repository.setFileName("MailActionRepositoryTest.json");		
		
	}
	
	
	@Test
	public void test_OK_SaveAndGetById() {
		
		repository.deleteAll();
		MailAction action = repository.save(buildMailAction());
		Assert.assertNotNull(repository.get(action.getId()));
		
	}

	@Test
	public void test_OK_GetAll() {

		repository.deleteAll();
		repository.save(buildMailAction());
		repository.save(buildMailAction());
		repository.save(buildMailAction());		
	
		Assert.assertEquals(3, repository.getAll().size());
		
	}

	@Test
	public void test_OK_Delete() {

		repository.deleteAll();
		MailAction id1 = repository.save(buildMailAction());
		MailAction id2 = repository.save(buildMailAction());
		MailAction id3 = repository.save(buildMailAction());
	
		repository.delete(id1);
		repository.delete(id3);		
		
		
		Assert.assertEquals(1, repository.getAll().size());
		Assert.assertEquals(id2.getId(), repository.getAll().get(0).getId());		
		
	}
	
	private MailAction buildMailAction() {
		MailAction action = new MailAction();
		action.setHost("smtp.gmail.com");
		action.setPort("587");
		action.setAuthentication("true");
		action.setUsername("user@domain.com");
		action.setPassword("pwd");
		action.setSubject("subject");
		action.setEncryptType(EncryptType.TLS);	//set the encrypt to use		
		
		return action;
	}
	
	
}
