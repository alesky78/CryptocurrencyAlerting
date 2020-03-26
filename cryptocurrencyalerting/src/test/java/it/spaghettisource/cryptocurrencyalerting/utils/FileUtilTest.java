package it.spaghettisource.cryptocurrencyalerting.utils;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import it.spaghettisource.cryptocurrencyalerting.exception.BaseException;
import it.spaghettisource.cryptocurrencyalerting.exception.ExceptionFactory;
import it.spaghettisource.cryptocurrencyalerting.i18n.MessageRepository;
import it.spaghettisource.cryptocurrencyalerting.i18n.StringMessageHelper;

public class FileUtilTest {

	
	ExceptionFactory exceptionFactory = new ExceptionFactory();
	String filePath;
	
	@Before
	public void beforeTest() {
		
		//prepare the exception factory
		MessageRepository repository =new MessageRepository();
		repository.setMessageRepositoryBundleBaseName("it.spaghettisource.cryptocurrencyalerting.utils.FileUtilTest");
		StringMessageHelper helper = new StringMessageHelper();
		helper.setMessageRepository(repository);
		exceptionFactory = new ExceptionFactory();
		exceptionFactory.setMessageHelper(helper);
		
		filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\it\\spaghettisource\\cryptocurrencyalerting\\utils";
		
	}
	
	@Test
	public void test_OK_readFile() {
		
		FileUtil utils = new FileUtil();
		String content = utils.readFileToString(exceptionFactory, filePath, "FileUtilTestReadFile.txt");
		
		System.out.println(content);
		Assert.assertNotNull(content);
		
	}
	
	@Test(expected= BaseException.class)
	public void test_KO_readFile() {
		
		FileUtil utils = new FileUtil();
		String content = utils.readFileToString(exceptionFactory, filePath, "NoFileName.txt");
		
		System.out.println(content);
		Assert.assertNotNull(content);
		
	}
	
	@Test
	public void test_OK_writeFile() {
		
		FileUtil utils = new FileUtil();
		utils.writeStringToFile(exceptionFactory, filePath, "FileUtilTestWriteFile.txt",System.currentTimeMillis()+" time");
		
		Assert.assertTrue(true);
		
	}
	
	
}
