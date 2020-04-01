package it.spaghettisource.cryptocurrencyalerting.repository;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import it.spaghettisource.cryptocurrencyalerting.action.SmtpMailAction;
import it.spaghettisource.cryptocurrencyalerting.action.SmtpMailAction.EncryptType;
import it.spaghettisource.cryptocurrencyalerting.exception.BaseException;
import it.spaghettisource.cryptocurrencyalerting.exception.ExceptionFactory;
import it.spaghettisource.cryptocurrencyalerting.i18n.MessageRepository;
import it.spaghettisource.cryptocurrencyalerting.i18n.StringMessageHelper;
import it.spaghettisource.cryptocurrencyalerting.utils.FileUtil;
import junit.framework.Assert;

public class SmtpMailActionRepositoryTest {

	ExceptionFactory exceptionFactory = new ExceptionFactory();
	SmtpMailActionRepository repository;
	
	String repositoryFilePath;
	String repositoryFileName;
	
	@Before
	public void beforeTest() {
		
		//prepare the exception factory
		MessageRepository messageRepository =new MessageRepository();
		messageRepository.setMessageRepositoryBundleBaseName("it.spaghettisource.cryptocurrencyalerting.repository.SmtpMailActionRepositoryTest");
		StringMessageHelper helper = new StringMessageHelper();
		helper.setMessageRepository(messageRepository);
		exceptionFactory = new ExceptionFactory();
		exceptionFactory.setMessageHelper(helper);
		
		repository = new SmtpMailActionRepository(exceptionFactory);	
		
		//overwrite to put the data in the test resources
		//this is not needed in the code, the repository keep internally its configuration
		repositoryFilePath = System.getProperty("user.dir")+"\\src\\test\\resources\\it\\spaghettisource\\cryptocurrencyalerting\\repository";
		repositoryFileName = "SmtpMailActionRepositoryTest.json";
				
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
		SmtpMailAction action = repository.save(buildMailAction(null));
		Assert.assertNotNull(repository.get(action.getId()));
		
	}

	@Test(expected = BaseException.class)
	public void test_KO_DuplicatePK() {
		
		repository.deleteAll();
		SmtpMailAction action = repository.save(buildMailAction(null));
		repository.save(action);		
	}	
	
	@Test
	public void test_OK_Update() {
		
		repository.deleteAll();
		SmtpMailAction action = repository.save(buildMailAction("id"));
		String userName= action.getUsername();
		
		action.setUsername("new userName");
		repository.update(action);
		action = repository.get(action.getId());
		
		Assert.assertTrue(!userName.equals(action.getUsername()));		
	}		
	
	@Test(expected = BaseException.class)
	public void test_KO_EntityNotFound() {
		
		repository.deleteAll();
		repository.update(buildMailAction(null));
	}		
	
	@Test
	public void test_OK_GetAll() {

		repository.deleteAll();
		repository.save(buildMailAction(null));
		repository.save(buildMailAction(null));
		repository.save(buildMailAction(null));		
	
		Assert.assertEquals(3, repository.getAll().size());
		
	}

	@Test
	public void test_OK_Delete() {

		repository.deleteAll();
		SmtpMailAction id1 = repository.save(buildMailAction(null));
		SmtpMailAction id2 = repository.save(buildMailAction(null));
		SmtpMailAction id3 = repository.save(buildMailAction(null));
	
		repository.delete(id1);
		repository.delete(id3);		
		
		
		Assert.assertEquals(1, repository.getAll().size());
		Assert.assertEquals(id2.getId(), repository.getAll().get(0).getId());		
		
	}
	
	private SmtpMailAction buildMailAction(String id) {
		SmtpMailAction action = new SmtpMailAction();
		action.setHost("smtp.gmail.com");
		action.setId(id);
		action.setPort("587");
		action.setAuthentication("true");
		action.setUsername("user@domain.com");
		action.setPassword("pwd");
		action.setSubject("subject");
		action.setEncryptType(EncryptType.TLS);	//set the encrypt to use		
		
		return action;
	}
	
	
}
