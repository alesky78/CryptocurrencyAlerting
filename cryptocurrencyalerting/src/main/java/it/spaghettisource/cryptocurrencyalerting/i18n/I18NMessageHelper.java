package it.spaghettisource.cryptocurrencyalerting.i18n;

import java.util.Locale;

/**
 * <code>I18NHelper</code> provides interface to get messages by an id and passing the parameters to valorize it
 *
 * 
 * @author Alessandro D'Ottavio
 * @version 1.0
 *
 */
public interface I18NMessageHelper {

	/**
	 * Utility to support {@link MessageFormat}
	 * it return a formatted message internazionalized by the system locale 
	 * 
	 * @param messageId of the message to find from the error Messages bundle
	 * @param arguments that will be injected in the message
	 * @return the message formatted and internazionalized
	 */
	public String getFormattedMessageI18N(String messageId, Object... arguments);
	
	/**
	 * Utility to support {@link MessageFormat}
	 * it return a formatted message internazionalized by the locale 
	 * 
	 * @param messageId of the message to find from the error Messages bundle
	 * @param arguments that will be injected in the message
	 * @return the message formatted and internazionalized
	 */
	public String getFormattedMessageI18N(Locale locale,String messageId, Object... arguments);
	
}
