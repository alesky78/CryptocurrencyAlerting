package it.spaghettisource.cryptocurrencyalerting.services;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import it.spaghettisource.cryptocurrencyalerting.action.ActionType;
import it.spaghettisource.cryptocurrencyalerting.action.SmtpMailAction;
import it.spaghettisource.cryptocurrencyalerting.exception.ExceptionFactory;
import it.spaghettisource.cryptocurrencyalerting.i18n.MessageRepository;
import it.spaghettisource.cryptocurrencyalerting.i18n.StringMessageHelper;

public class ActionServiceTest {

	ActionService actionService;

	
	@Before
	public void beforeTest() {
		
		//prepare the exception factory
		MessageRepository messageRepository =new MessageRepository();
		messageRepository.setMessageRepositoryBundleBaseName("it.spaghettisource.cryptocurrencyalerting.services.ActionServiceTest");
		StringMessageHelper helper = new StringMessageHelper();
		helper.setMessageRepository(messageRepository);
		ExceptionFactory exceptionFactory = new ExceptionFactory();
		exceptionFactory.setMessageHelper(helper);
		
		actionService = new ActionService(exceptionFactory);
				
	}
	
	
	@Test
	public void test_OK_FindAction() {
		
		
		SmtpMailAction action = (SmtpMailAction)actionService.findAction(ActionType.SmtpMailAction);
		
		Assert.assertNotNull(action);
	}	
	
	

	
	
}
