package it.spaghettisource.cryptocurrencyalerting.action;

import java.awt.Frame;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.spaghettisource.cryptocurrencyalerting.exception.ExceptionFactory;
import it.spaghettisource.cryptocurrencyalerting.ui.AppSwingUIManager;

/**
 * create a Dialogs on the screen
 * 
 * 
 * @author Alessandro D'Ottavio
 * @version 1.0
 */
public class PopupAction extends AbstractAction {

	static Logger log = LoggerFactory.getLogger(PopupAction.class);


	public PopupAction(ExceptionFactory exceptionFactory) {
		super();
		init(exceptionFactory);
	}


	private void init(ExceptionFactory exceptionFactory) {
		actionType = ActionType.PopupAction;

	}


	@Override
	public void trigger(String message) {

		Thread thread = new Thread(new OpenAsyncPopup(message), "Thread open popup");
		thread.start();

	}



	class OpenAsyncPopup implements Runnable{

		private String message;


		public OpenAsyncPopup(String message) {
			super();
			this.message = message;
		}

		@Override
		public void run() {
			//send the message
			Frame[] frames = Frame.getFrames();

			for (int i = 0; i < frames.length; i++) {
				if(AppSwingUIManager.MAIN_FRAME_TITLE.equals(frames[i].getTitle())) {

					log.debug("open the popup");
					
					//move to foreground and deiconize
					if (!frames[i].isActive()) {
						frames[i].setState(JFrame.ICONIFIED);
						frames[i].setState(JFrame.NORMAL);
					}					

					JOptionPane.showMessageDialog(frames[i],
							message,
							"Alert !!!!",
							JOptionPane.WARNING_MESSAGE);
				}
			}

		}

	}





}
