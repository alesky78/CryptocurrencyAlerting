package it.spaghettisource.cryptocurrencyalerting.http;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.WebResource.Builder;

import it.spaghettisource.cryptocurrencyalerting.exception.BaseException;
import it.spaghettisource.cryptocurrencyalerting.exception.ExceptionFactory;
import it.spaghettisource.cryptocurrencyalerting.i18n.MessageRepository;
import it.spaghettisource.cryptocurrencyalerting.i18n.StringMessageHelper;

public class CryptoCurrencyHttpJerseyClientTest {

	ExceptionFactory exceptionFactory = new ExceptionFactory();
	HttpClientJerseyTest client;
	
	@Before
	public void beforeTest() {
		
		//prepare the exception factory
		MessageRepository messageRepository =new MessageRepository();
		messageRepository.setMessageRepositoryBundleBaseName("it.spaghettisource.cryptocurrencyalerting.http.CryptoCurrencyHttpJerseyClientTest");
		StringMessageHelper helper = new StringMessageHelper();
		helper.setMessageRepository(messageRepository);
		exceptionFactory = new ExceptionFactory();
		exceptionFactory.setMessageHelper(helper);	
		
		client = new HttpClientJerseyTest(exceptionFactory);
		
	}
	
	
	
	@Test
	public void testExampleCall() {
						
		String response = client.doGet("https://www.google.com/");
		System.out.print(response);
		Assert.assertNotNull(response);
		
	}
	
	
	/**
	 * basic implementatio of the CryptoCurrencyHttpJerseyClient to perfor test over the abstract class
	 * 
	 * @author Alessandro
	 *
	 */
	public class HttpClientJerseyTest extends CryptoCurrencyHttpJerseyClient{

		public HttpClientJerseyTest(ExceptionFactory exceptionFactory) {
			this.exceptionFactory = exceptionFactory;
			this.httpsConnectorFactory = new ApacheHttpConnectionFactoryDefault();
		}
		
		
		@Override
		protected void checkResponseStatusCode(int statusCode) throws BaseException {
			if (statusCode != 200) {
				throw exceptionFactory.getHttp400();
			}
		}

		@Override
		protected Builder configureRequest(WebResource builder) {
			return builder.getRequestBuilder();
		}
		
	}
	
}
