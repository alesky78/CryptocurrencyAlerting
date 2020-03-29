package it.spaghettisource.cryptocurrencyalerting.http;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.client.HttpClient;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.SingleClientConnManager;

import it.spaghettisource.cryptocurrencyalerting.exception.ExceptionFactory;

/**
 * legacy implementation of the SSL httpClient that skip any certificate control over the https and consider any call calid
 * 
 * @author Alessandro
 *
 */
@SuppressWarnings("deprecation")
public class ApacheHttpConnectionFactorySSLSkip implements ApacheHttpConnectionFactory{

	
	/**
	 * legacy implementation im forcing to don't check any https controll
	 *
	 * @return
	 */
	public HttpClient builApacheHttpsClient(ExceptionFactory exceptionFactory){


		SSLContext sslContext = null;
		try {
			sslContext = SSLContext.getInstance("SSL");
		} catch (NoSuchAlgorithmException cause) {
			throw exceptionFactory.getSSLProtocolNotSupported(cause);
		}

		// set up a TrustManager that trusts everything
		try {
			sslContext.init(null, new TrustManager[] { new X509TrustManager() {
			            public X509Certificate[] getAcceptedIssuers() {
			                    return null;
			            }

			            public void checkClientTrusted(X509Certificate[] certs,String authType) {
			            }

			            public void checkServerTrusted(X509Certificate[] certs,
			                            String authType) {
			            }
			} }, new SecureRandom());
		} catch (KeyManagementException e) {

			e.printStackTrace();
		}

		SSLSocketFactory sf = new SSLSocketFactory(sslContext);
		Scheme httpsScheme = new Scheme("https", 443, sf);
		SchemeRegistry schemeRegistry = new SchemeRegistry();
		schemeRegistry.register(httpsScheme);

		// apache HttpClient version >4.2 should use BasicClientConnectionManager
		ClientConnectionManager cm = new SingleClientConnManager(schemeRegistry);

		return new DefaultHttpClient(cm);
	}

}
