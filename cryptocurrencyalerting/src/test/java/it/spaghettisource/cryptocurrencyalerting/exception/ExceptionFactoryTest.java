package it.spaghettisource.cryptocurrencyalerting.exception;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import it.spaghettisource.cryptocurrencyalerting.i18n.MessageRepository;
import it.spaghettisource.cryptocurrencyalerting.i18n.StringMessageHelper;

public class ExceptionFactoryTest {

	private StringMessageHelper helper;
	
	@Before
	public void beforeTest() {
		//prepare the repository
		MessageRepository repository =new MessageRepository();
		repository.setMessageRepositoryBundleBaseName("it.spaghettisource.cryptocurrencyalerting.exception.ExceptionFactory");
		helper = new StringMessageHelper();
		helper.setMessageRepository(repository);
	}
	
	
	@Test
	public void test_OK_createUnespectedException() {
		
		ExceptionFactory factory = new ExceptionFactory();
		factory.setMessageHelper(helper);
		
		BaseException exception = factory.getUnexpectedException(null);
		
		System.out.println(exception.getMessage());
		Assert.assertNotNull(exception.getMessage());
	}
	
}
