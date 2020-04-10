package it.spaghettisource.cryptocurrencyalerting.action;

import java.awt.Frame;
import java.util.Properties;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.spaghettisource.cryptocurrencyalerting.exception.ExceptionFactory;
import it.spaghettisource.cryptocurrencyalerting.ui.AppSwingUIManager;
import it.spaghettisource.cryptocurrencyalerting.utils.FileUtil;

/**
 * create a Dialogs on the screen and emit sound
 * 
 * 
 * @author Alessandro D'Ottavio
 * @version 1.0
 */
public class PopupAction extends AbstractAction {

	static Logger log = LoggerFactory.getLogger(PopupAction.class);
	
	public static String CONFIG_FILE_PATH = System.getProperty("user.dir")+ System.getProperty("file.separator")+"configuration\\action\\";
	public static String CONFIG_FILE_NAME = "popup.properties";

	private Properties prop;



	public PopupAction(ExceptionFactory exceptionFactory) {
		super();
		init(exceptionFactory, CONFIG_FILE_PATH, CONFIG_FILE_NAME);
	}


	private void init(ExceptionFactory exceptionFactory, String configFilePath, String configFileName) {
		actionType = ActionType.PopupAction;

		//load the specific property configuration file
		prop = new Properties();
		try {
			prop.load(FileUtil.readFileToInputStream(exceptionFactory, configFilePath, configFileName));	
		}catch (Exception cause) {
			throw exceptionFactory.getImpossibleReadFileException(cause, configFilePath, configFileName);
		}
		
	}


	@Override
	public void trigger(String message) {

		Thread thread = new Thread(new OpenAsyncPopup(message), "Thread open popup");
		thread.start();

	}



	class OpenAsyncPopup implements Runnable{

		private String message;
		private Clip clip;
		private boolean makeSound;
		private boolean loopSound;
		private boolean foregroundPopup;
		


		public OpenAsyncPopup(String message) {
			super();
			this.message = message;
			makeSound = Boolean.valueOf(prop.getProperty("makeSound"));
			loopSound = Boolean.valueOf(prop.getProperty("loopSound"));
			foregroundPopup = Boolean.valueOf(prop.getProperty("foregroundPopup"));
		}

		@Override
		public void run() {
			//send the message
			Frame[] frames = Frame.getFrames();

			for (int i = 0; i < frames.length; i++) {
				if(AppSwingUIManager.MAIN_FRAME_TITLE.equals(frames[i].getTitle())) {

					try {
						
						if(makeSound) {
							clip = AudioSystem.getClip();
							AudioInputStream inputStream = AudioSystem.getAudioInputStream(this.getClass().getResourceAsStream("/sound/beep.wav"));           
							clip.open(inputStream);
							if(loopSound) {
								clip.loop(clip.LOOP_CONTINUOUSLY);								
							}
							clip.start();							
						}


						log.debug("open the popup");

						//move to foreground
						if(foregroundPopup) {
							if (!frames[i].isActive()) {
								frames[i].setState(JFrame.ICONIFIED);
								frames[i].setState(JFrame.NORMAL);
							}							
						}
					

						JOptionPane.showMessageDialog(frames[i],
								message,
								"Alert !!!!",
								JOptionPane.WARNING_MESSAGE);

						if(makeSound) {
							clip.stop();							
						}


					}catch (Exception e) {
						log.error("error executing the PopupAction",e);
					}

				}
			}

		}

	}

}
