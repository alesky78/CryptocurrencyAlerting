package it.spaghettisource.cryptocurrencyalerting.services;

import java.lang.reflect.Constructor;
import java.util.Properties;

import it.spaghettisource.cryptocurrencyalerting.exception.ExceptionFactory;
import it.spaghettisource.cryptocurrencyalerting.i18n.MessageRepository;
import it.spaghettisource.cryptocurrencyalerting.i18n.StringMessageHelper;
import it.spaghettisource.cryptocurrencyalerting.provider.MarketAdapter;
import it.spaghettisource.cryptocurrencyalerting.provider.coinmarketcap.ConstantCoinMarketCap;
import it.spaghettisource.cryptocurrencyalerting.utils.FileUtil;

/**
 * ServiceLocator is responsible to load and distribute all the instance of the requested services
 * 
 * @author Alessandro D'Ottavio
 * @version 1.0
 */
public class ServiceLocator {

	private static ServiceLocator serviceLocator;

	public static String CONFIG_FILE_PATH = System.getProperty("user.dir")+ System.getProperty("file.separator")+"configuration";
	public static String CONFIG_FILE_NAME = "configuration.properties";	

	private Properties configuration;

	//infrastructure
	private StringMessageHelper messageHelper;
	private ExceptionFactory exceptionFactory;
	private MarketAdapter marketAdapter;
	
	//services
	private ActionService actionService;
	private AlertService alertService;	


	private ServiceLocator() {
		super();
	}

	public static ServiceLocator getInstance() {
		if(serviceLocator==null) {
			serviceLocator = new ServiceLocator();
			serviceLocator.init();
		}
		return serviceLocator;
	}


	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void init() {


		//load the specific property configuration file
		configuration = new Properties();
		try {
			configuration.load(FileUtil.readFileToInputStream(exceptionFactory, CONFIG_FILE_PATH, CONFIG_FILE_NAME));	
		}catch (Exception cause) {
			throw exceptionFactory.getImpossibleReadFileException(cause, ConstantCoinMarketCap.CONFIG_FILE_PATH, ConstantCoinMarketCap.CONFIG_FILE_NAME);
		}

		//prepare the messages repository
		MessageRepository messageRepository = new MessageRepository();
		messageRepository.setMessageRepositoryBundleBaseName("it.spaghettisource.cryptocurrencyalerting.i18n.message");
		messageHelper = new StringMessageHelper();
		messageHelper.setMessageRepository(messageRepository);

		//prepare the exception factory
		exceptionFactory = new ExceptionFactory();		
		exceptionFactory.setMessageHelper(messageHelper);

		//prepare the adapter to use
		try {
			String marketAdapterClass = configuration.getProperty("marketAdapter.class");
			Class clazz = this.getClass().forName(marketAdapterClass);
			Constructor constructor = clazz.getConstructor(ExceptionFactory.class);
			marketAdapter =	(MarketAdapter) constructor.newInstance(exceptionFactory);

		} catch (Exception e) {
			e.printStackTrace();
		}

		//prepare the action manager
		actionService = new ActionService(exceptionFactory);
		alertService = new AlertService(exceptionFactory);

	}

	
	public StringMessageHelper getMessageHelper() {
		return messageHelper;
	}

	public MarketAdapter getMarketAdapter() {
		return marketAdapter;
	}

		

}
