package it.spaghettisource.cryptocurrencyalerting.i18n;

import java.util.Locale;
import java.util.ResourceBundle;


/**
 * <code>MessageRepository</code> provides utilities to get messages from a resource bundle element.
 *
 * 
 * @author Alessandro D'Ottavio
 * @version 1.0
 *
 */
public class MessageRepository {

	
	private String resourceBundleFile;

	/**
	 * set the base bundle name to use to internationalize the message
	 * 
	 * @param errorMessageBundleFile
	 */
	public void setMessageRepositoryBundleBaseName(String resourceBundleFile) {
		this.resourceBundleFile = resourceBundleFile; 
	}

	/**
	 * get a message by id using the default locale configured in the JVM
	 * 
	 * @param messageId
	 * @return
	 */
	public  String getMessageById(String messageId){

        ResourceBundle messages;
        
        messages = ResourceBundle.getBundle(resourceBundleFile);
        
		return messages.getString(messageId);
		
	}

	/**
	 * get a message by id using the locale configured in the JVM
	 * 
	 * @param messageId
	 * @param locale
	 * @return
	 */
	public  String getMessageById(String messageId,Locale locale){

        ResourceBundle messages;
        messages = ResourceBundle.getBundle(resourceBundleFile,locale);
        
		return messages.getString(messageId);
		
	}
	
	
}
