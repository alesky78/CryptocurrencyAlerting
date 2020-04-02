package it.spaghettisource.cryptocurrencyalerting.app;


import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import it.spaghettisource.cryptocurrencyalerting.ui.AppSwingUIManager;

/**
 * entry point of the application
 *
 * @author Alessandro D'Ottavio
 * @version 1.0
 *
 */
public class Application {

	static Log log = LogFactory.getLog(Application.class.getName());

	private Application(){
	}



	public static void main(String[] args) throws Exception{

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

		//start the UI application
		try {
			
			AppSwingUIManager uiManager = new AppSwingUIManager();
			uiManager.startUI();
			
		} catch (Exception ex) {
			log.fatal("unexpected error starting the application", ex);
			System.exit(-1);
		}

	}



}
