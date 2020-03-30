package it.spaghettisource.cryptocurrencyalerting.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.BasicCookieStore;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientHandler;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.WebResource.Builder;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;
import com.sun.jersey.client.apache4.ApacheHttpClient4Handler;

import it.spaghettisource.cryptocurrencyalerting.exception.BaseException;
import it.spaghettisource.cryptocurrencyalerting.exception.ExceptionFactory;


/**
 * <code>CryptoCurrencyHttpApacheClient</code> implementation of the {@link CryptoCurrencyHttpClient} 
 * that use the apahce http client https://hc.apache.org/
 *
 * 
 * @author Alessandro D'Ottavio
 * @version 1.0
 *
 */
public abstract class  CryptoCurrencyHttpJerseyClient implements CryptoCurrencyHttpClient {

	private static final int GET = 0;
	private static final int POST = 1;
	private static final int PUT = 2;
	private static final int DELETE = 3;
	
	protected ExceptionFactory exceptionFactory;
	protected ApacheHttpConnectionFactory httpsConnectorFactory;
	protected Client client;


	public String doGet(String service) throws BaseException{
		
		client = buildClient(false);
		WebResource webResource = buildWebResource(service);
		
		return executeRequest(webResource,GET,null);
	}

	
	public String doGet(String service,Map<String,String> queryParams) throws BaseException{

		client = buildClient(false);
		WebResource webResource = buildWebResource(service);

		for (String key : queryParams.keySet()) {
			webResource = webResource.queryParam(key, queryParams.get(key));
		}

		return executeRequest(webResource,GET,null);
	}	
	
	
	public String doDelete(String service,Map<String,String> queryParams) throws BaseException{

		client = buildClient(false);
		WebResource webResource = buildWebResource(service);
		
		for (String key : queryParams.keySet()) {
			webResource = webResource.queryParam(key, queryParams.get(key));
		}

		return executeRequest(webResource,DELETE,null);
	}

	
	public String doDelete(String service) throws BaseException{

		client = buildClient(false);
		WebResource webResource = buildWebResource(service);

		return executeRequest(webResource,DELETE,null);
	}
	
	
	public String doPost(String service,String body) throws BaseException{

		client = buildClient(false);
		WebResource webResource = buildWebResource(service);

		return executeRequest(webResource,POST,body);
	}	
	
	
	public String doPut(String service,String body) throws BaseException{

		client = buildClient(false);
		WebResource webResource = buildWebResource(service);

		return executeRequest(webResource,PUT,body);
	}	
	
	
	private Client buildClient(boolean pojoMapping) throws BaseException{

		HttpClient apacheClient = null;

		apacheClient = httpsConnectorFactory.builApacheHttpsClient(exceptionFactory);


		ClientHandler root = new ApacheHttpClient4Handler(apacheClient,new BasicCookieStore(),true);

		if(pojoMapping){
			ClientConfig clientConfig = new DefaultClientConfig();
			clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
			return new Client(root,clientConfig);

		}else{
			return new Client(root);
		}
	}	
	
	
	private WebResource buildWebResource(String service) {
		try {
			return client.resource(service);			
		}catch (Exception cause) {
			throw exceptionFactory.getInvalidURL(cause,service);
		}
	}	
	

	private String executeRequest(WebResource webResource,int type, String body) throws BaseException {

		ClientResponse response = null;

		if(type == GET){
			response = configureRequest(webResource).get(ClientResponse.class);
		}if(type == DELETE){
			response = configureRequest(webResource).delete(ClientResponse.class);
		}else if(type == POST){
			response = configureRequest(webResource).post(ClientResponse.class,body);
		}else if(type == PUT){
			response = configureRequest(webResource).put(ClientResponse.class,body);
		}

		//check if there is an error in the response and in case throw the specific BaseException
		checkResponseStatusCode(response.getStatus());
		
		return responseAsString(response);
	}	
	

	private String responseAsString(ClientResponse response) throws BaseException{
        InputStream stream = response.getEntityInputStream();
        StringBuilder textBuilder = new StringBuilder();
        try (Reader reader = new BufferedReader(new InputStreamReader(stream, Charset.forName(StandardCharsets.UTF_8.name())))) {
            int c = 0;
            while ((c = reader.read()) != -1) {
                textBuilder.append((char) c);
            }
            return textBuilder.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }	
	
	
	/**
	 * check the status code and decide the exception based on the specific implementation
	 * 
	 * @param statusCode
	 * @throws Exception
	 */
	protected abstract void checkResponseStatusCode(int statusCode) throws BaseException;
	
	
	/**
	 * configure the http call based on the specific implementation
	 * 
	 * @param builder
	 */
	protected abstract Builder configureRequest(WebResource builder);

	
}
