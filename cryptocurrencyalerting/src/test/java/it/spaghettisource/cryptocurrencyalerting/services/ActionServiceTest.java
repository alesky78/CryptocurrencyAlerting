package it.spaghettisource.cryptocurrencyalerting.services;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import it.spaghettisource.cryptocurrencyalerting.action.ActionType;
import it.spaghettisource.cryptocurrencyalerting.action.SmtpMailAction;
import it.spaghettisource.cryptocurrencyalerting.action.SmtpMailAction.EncryptType;
import it.spaghettisource.cryptocurrencyalerting.exception.ExceptionFactory;
import it.spaghettisource.cryptocurrencyalerting.i18n.MessageRepository;
import it.spaghettisource.cryptocurrencyalerting.i18n.StringMessageHelper;
import it.spaghettisource.cryptocurrencyalerting.repository.SmtpMailActionRepository;
import it.spaghettisource.cryptocurrencyalerting.utils.FileUtil;

public class ActionServiceTest {

	ActionService actionService;
	SmtpMailActionRepository repository;
	
	String repositoryFilePath;
	String repositoryFileName;
	
	@Before
	public void beforeTest() {
		
		//prepare the exception factory
		MessageRepository messageRepository =new MessageRepository();
		messageRepository.setMessageRepositoryBundleBaseName("it.spaghettisource.cryptocurrencyalerting.services.ActionServiceTest");
		StringMessageHelper helper = new StringMessageHelper();
		helper.setMessageRepository(messageRepository);
		ExceptionFactory exceptionFactory = new ExceptionFactory();
		exceptionFactory.setMessageHelper(helper);
		
		repository = new SmtpMailActionRepository(exceptionFactory);	
		
		//overwrite to put the data in the test resources
		//this is not needed in the code, the repository keep internally its configuration
		repositoryFilePath = System.getProperty("user.dir")+"\\src\\test\\resources\\it\\spaghettisource\\cryptocurrencyalerting\\services";
		repositoryFileName = "actionServiceTest_SmtpMailActionRepository.json";
				
		repository.setFilePath(repositoryFilePath);
		repository.setFileName(repositoryFileName);	
		
		actionService = new ActionService(exceptionFactory, repository);
				
	}
	
	
	@After
	public void afterTest() {
		FileUtil.deleteFile(repositoryFilePath, repositoryFileName);
	}		
	
	
	@Test
	public void test_OK_SaveNewAction() {
		
		repository.deleteAll();
		actionService.saveNewAction(buildMailAction("action"));
		repository.deleteAll();		
		
		Assert.assertTrue(true);
	}
	
	@Test
	public void test_OK_UpdateAction() {
		
		repository.deleteAll();
		SmtpMailAction action = buildMailAction("action");
		actionService.saveNewAction(action);
		action.setHost("other host");
		actionService.updateAction(action);
		repository.deleteAll();	
		
		Assert.assertTrue(true);
	}
	
	@Test
	public void test_OK_FindAction() {
		
		repository.deleteAll();
		String id = "FindAction";
		SmtpMailAction action = buildMailAction(id);
		actionService.saveNewAction(action);
		action = (SmtpMailAction)actionService.findAction(ActionType.SmtpMailAction, id);
		repository.deleteAll();		
		
		Assert.assertNotNull(action);
	}	
	
	@Test
	public void test_OK_FindAllActionId() {
		
		repository.deleteAll();
		String id = "FindAllAction";
		actionService.saveNewAction(buildMailAction(id+"1"));
		actionService.saveNewAction(buildMailAction(id+"2"));
		actionService.saveNewAction(buildMailAction(id+"3"));		
		List<String> actionsId = actionService.findAllActionId(ActionType.SmtpMailAction);
		repository.deleteAll();		
		
		Assert.assertTrue(actionsId.size()==3);
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
