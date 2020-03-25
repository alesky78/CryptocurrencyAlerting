package it.spaghettisource.cryptocurrencyalerting.i18n;

import java.util.Locale;

import org.junit.Assert;
import org.junit.Test;

public class MessageRepositoryTest {

	@Test
	public void test_OK_geKeyFormDefaultBundle() {
		
		MessageRepository repository =new MessageRepository();
		repository.setMessageRepositoryBundleBaseName("it.spaghettisource.cryptocurrencyalerting.i18n.MessageRepositoryTest");
		String key = repository.getMessageById("key.1");

		System.out.println(key);
		Assert.assertNotNull(key);
		
	}
	
	@Test
	public void test_OK_geKeyFormSpecificBundle() {
		
		MessageRepository repository =new MessageRepository();
		repository.setMessageRepositoryBundleBaseName("it.spaghettisource.cryptocurrencyalerting.i18n.MessageRepositoryTest");
		
		Locale currentLocale = new Locale("en");
		String key = repository.getMessageById("key.1",currentLocale);
		
		System.out.println(key);
		Assert.assertNotNull(key);
		
	}
	
}
