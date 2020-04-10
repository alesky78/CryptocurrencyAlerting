package it.spaghettisource.cryptocurrencyalerting.app;


import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.spaghettisource.cryptocurrencyalerting.agent.AgentController;
import it.spaghettisource.cryptocurrencyalerting.services.ServiceLocator;
import it.spaghettisource.cryptocurrencyalerting.ui.AppSwingUIManager;

/**
 * entry point of the application
 * 
 * it is responsible to start up and shutdown all the services 
 *
 * @author Alessandro D'Ottavio
 * @version 1.0
 *
 */
public class Application  implements Runnable {

	static Logger log = LoggerFactory.getLogger(Application.class);

	private Application(){
	}



	public static void main(String[] args) throws Exception{

		Application application = new Application();
		
    	log.info("start the application");
		
		//set the look and feel of swing
		try {
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        if ("Nimbus".equals(info.getName())) {
			    	log.info("loading Ninbus LoolAndFeel");
		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		    }
		} catch (Exception e) {
			log.error("Nimbus is not available, default GUI look and feel will be used",e);
		}

		
    	//register a shutdown Handle
        Runtime.getRuntime().addShutdownHook(new Thread(application));

		
		//start the application
		try {
			
			//start the services
			ServiceLocator.initialize();
			
			//start the UI
			AppSwingUIManager uiManager = new AppSwingUIManager();
			uiManager.startUI();
			
			//start the agent that check the allert
			ServiceLocator.getInstance().getAgentController().start();
			
			
		} catch (Exception ex) {
			log.error("errro statring the application",ex);
			System.exit(-1);
		}
		
    	log.info("start succesfully");




        
	}


	/**
	 * execute when the application shutdown
	 * 
	 */
	public void run() {

		AgentController agent = ServiceLocator.getInstance().getAgentController();
		if(agent!=null) {
			agent.shutdown();
			log.info("shutdown the agent");
		}
		
    	log.info("shutdown completed");
    	log.info("bye");			

	}



}
