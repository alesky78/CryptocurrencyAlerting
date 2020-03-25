package it.spaghettisource.cryptocurrencyalerting.i18n;

import java.util.Locale;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class StringMessageHelperTest {

	private MessageRepository repository;
	
	@Before
	public void beforeTest() {
		//prepare the repository
		repository =new MessageRepository();
		repository.setMessageRepositoryBundleBaseName("it.spaghettisource.cryptocurrencyalerting.i18n.StringMessageHelperTest");
	}
	
	
	@Test
	public void test_OK_generateMessageFormDefaultBundle() {
		
		StringMessageHelper helper = new StringMessageHelper();
		helper.setMessageRepository(repository);
		
		Object[] parameters = {new String("primo parametro")};
		String key = helper.getFormattedMessageI18N("key.1", parameters);
		
		System.out.println(key);
		Assert.assertNotNull(key);
		
	}

	
	@Test
	public void test_OK_generateMessageFormSpecificBundle() {
		
		StringMessageHelper helper = new StringMessageHelper();
		helper.setMessageRepository(repository);
		
		Locale currentLocale = new Locale("en");
		
		Object[] parameters = {new String("first parameter")};
		String key = helper.getFormattedMessageI18N(currentLocale,"key.1", parameters);
		
		System.out.println(key);
		Assert.assertNotNull(key);
		
	}	
}
