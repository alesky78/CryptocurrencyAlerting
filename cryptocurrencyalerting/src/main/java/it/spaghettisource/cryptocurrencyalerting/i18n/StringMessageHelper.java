package it.spaghettisource.cryptocurrencyalerting.i18n;


import java.text.MessageFormat;
import java.util.Locale;

/**
 * <code>StringMessageHelper</code> provides utilities to produce complex messages
 * or support {@link MessageFormat}  
 *
 * 
 * @author Alessandro D'Ottavio
 * @version 1.0
 *
 */
public class StringMessageHelper implements I18NMessageHelper{
	
	private MessageRepository messageRepository;
	
	/**
	 * set the message repository
	 * 
	 * @param messageRepository
	 */
	public void setMessageRepository(MessageRepository messageRepository) {
		this.messageRepository = messageRepository;
	}

	/**
	 * Utility to support {@link MessageFormat}
	 * it return a formatted message internazionalized by the system locale 
	 * 
	 * @param messageId of the message to find from the error Messages bundle
	 * @param arguments that will be injected in the message
	 * @return the message formatted and internazionalized
	 */
	public String getFormattedMessageI18N(String messageId, Object... arguments)
	{
		String message = messageRepository.getMessageById(messageId);
		return getFormattedMessage(message, arguments);
	}

	/**
	 * Utility to support {@link MessageFormat}
	 * it return a formatted message internazionalized by the locale 
	 * 
	 * @param messageId of the message to find from the error Messages bundle
	 * @param arguments that will be injected in the message
	 * @return the message formatted and internazionalized
	 */
	public String getFormattedMessageI18N(Locale locale,String messageId, Object... arguments)
	{
		String message = messageRepository.getMessageById(messageId,locale);
		return getFormattedMessage(message, arguments);
	}
	
	
	/**
	 * Utility to support {@link MessageFormat}
	 * it return a formatted message 
	 * 
	 * @param message that follow the {@link MessageFormat} convention
	 * @param arguments that will be injected in the message
	 * @return the message formatted
	 */
	private static String getFormattedMessage(String message, Object... arguments)
	{
		return MessageFormat.format(message, arguments);
	}	
	
	
}
