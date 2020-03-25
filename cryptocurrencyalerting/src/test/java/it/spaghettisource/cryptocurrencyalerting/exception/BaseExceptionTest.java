package it.spaghettisource.cryptocurrencyalerting.exception;

import java.util.Locale;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import it.spaghettisource.cryptocurrencyalerting.i18n.MessageRepository;
import it.spaghettisource.cryptocurrencyalerting.i18n.StringMessageHelper;

public class BaseExceptionTest {

	private StringMessageHelper helper;
	
	@Before
	public void beforeTest() {
		//prepare the repository
		MessageRepository repository =new MessageRepository();
		repository.setMessageRepositoryBundleBaseName("it.spaghettisource.cryptocurrencyalerting.exception.BaseExceptionTest");
		helper = new StringMessageHelper();
		helper.setMessageRepository(repository);
	}
	
	
	@Test
	public void test_OK_createExceptionWithDefaultBundle() {
		
		Object[] parameters = {new String("primo parametro")};
		
		BaseException exception = new BaseException("key.1", parameters);
		exception.setMessageHelper(helper);
		
		System.out.println(exception.getMessage());
		Assert.assertNotNull(exception.getMessage());
	}

	
	@Test
	public void test_OK_createExceptionWithSpecificBundle() {
				
		Object[] parameters = {new String("first parameter")};
		
		BaseException exception = new BaseException("key.1", parameters);
		exception.setMessageHelper(helper);
		
		Locale currentLocale = new Locale("en");
		System.out.println(exception.getMessage(currentLocale));
		Assert.assertNotNull(exception.getMessage());
		
	}	
}
