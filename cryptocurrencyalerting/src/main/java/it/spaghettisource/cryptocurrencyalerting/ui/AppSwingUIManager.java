package it.spaghettisource.cryptocurrencyalerting.ui;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import it.spaghettisource.cryptocurrencyalerting.utils.ImageIconFactory;

/**
 * This is the manager of the UI swing application
 * is responsible to create the MainFrame and Thread for the application
 *
 * @author Alessandro D'Ottavio
 *
 */
public class AppSwingUIManager{

	static Log log = LogFactory.getLog(AppSwingUIManager.class.getName());

	/**
	 * this are the TAB used in the main page
	 */
	private final static String TAB_VERSION = "version";		


	private JFrame mainFrame;
	
	private JPanel panelVersion;	


	public void startUI(){

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				mainFrame = buildFrame();
				mainFrame.setVisible(true);
			}
		});

	}


	private JFrame buildFrame(){


		////////////////////////
		//prepare the main frame
		////////////////////////
		JFrame frame = new JFrame();
		frame.setIconImage(ImageIconFactory.getAppImage());
		frame.setTitle("cryptocurrency alerting");
		frame.setSize(1000, 800);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);

		//////////////////////////////////////////
		//add all the tabs to the main frame
		/////////////////////////////////////////
		JTabbedPane tabbedPane = new JTabbedPane();

		tabbedPane.addChangeListener(new TabModelChangeListener());	//refresh the model when enter in the tab model


		panelVersion = new JPanel();

		//tabbedPane.addTab(TAB_VERSION, ImageIconFactory.getForTab("version.png"),panelVersion);	
		tabbedPane.addTab(TAB_VERSION, panelVersion);

		frame.getContentPane().add(tabbedPane);

		////////////////////////////////
		//Center the frame in the screen
		////////////////////////////////
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
        log.debug("center the frame");
        log.debug("monitor width:"+dim.width+" height:"+dim.height);
        log.debug("frame width:"+frame.getSize().width+" height:"+frame.getSize().height);
		
		return frame;

	}





	/**
	 * this is the event listener used by the main JTabbedPane panel
	 * to refresh activities in case of TAB changed
	 */
	class TabModelChangeListener implements ChangeListener{

		public void stateChanged(ChangeEvent event) {

			if(event.getSource()!=null){
				if(event.getSource() instanceof JTabbedPane){
					JTabbedPane tabbedPane = (JTabbedPane)event.getSource();
					int selectedTabIndex = tabbedPane.getSelectedIndex();
					String tabName = tabbedPane.getTitleAt(selectedTabIndex);


					if(tabName.equals(TAB_VERSION)){
					}


				}
			}
		}
	}
	
	
}
