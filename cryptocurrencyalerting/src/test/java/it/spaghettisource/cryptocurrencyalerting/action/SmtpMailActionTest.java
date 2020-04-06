package it.spaghettisource.cryptocurrencyalerting.action;

import org.junit.Before;

import it.spaghettisource.cryptocurrencyalerting.exception.ExceptionFactory;
import it.spaghettisource.cryptocurrencyalerting.i18n.MessageRepository;
import it.spaghettisource.cryptocurrencyalerting.i18n.StringMessageHelper;

public class SmtpMailActionTest {

	private ExceptionFactory exceptionFactory;
	private String configFilePath;
	private String configFileName;
	
	@Before
	public void beforeTest() {
		
		//prepare the exception factory
		MessageRepository messageRepository = new MessageRepository();
		messageRepository.setMessageRepositoryBundleBaseName("it.spaghettisource.cryptocurrencyalerting.action.SmtpMailActionTestException");
		StringMessageHelper helper = new StringMessageHelper();
		helper.setMessageRepository(messageRepository);
		exceptionFactory = new ExceptionFactory();
		exceptionFactory.setMessageHelper(helper);
		
		//create the mail configuration
		configFilePath = System.getProperty("user.dir") +"\\src\\test\\resources\\it\\spaghettisource\\cryptocurrencyalerting\\action\\";
		configFileName = "SmtpMailActionTestMail.properties";
		
	}
	
	
	//confgure the provider to make the test or it will fail
	//@Test
	public void test_OK_sendMail() {

		SmtpMailAction action = new SmtpMailAction(exceptionFactory, configFilePath, configFileName);
		action.trigger("test message sent");
		
	}
		
}
