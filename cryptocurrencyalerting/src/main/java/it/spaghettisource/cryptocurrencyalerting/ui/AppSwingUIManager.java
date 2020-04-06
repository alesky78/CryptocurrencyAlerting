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

import it.spaghettisource.cryptocurrencyalerting.i18n.StringMessageHelper;
import it.spaghettisource.cryptocurrencyalerting.services.ServiceLocator;
import it.spaghettisource.cryptocurrencyalerting.ui.utils.ImageIconFactory;

/**
 * This is the manager of the UI swing application
 * is responsible to create the MainFrame and the Thread for the UI application
 *
 * @author Alessandro D'Ottavio
 * @version 1.0
 */
public class AppSwingUIManager{

	static Log log = LogFactory.getLog(AppSwingUIManager.class.getName());

	/**
	 * this are the TAB used in the main page
	 */
	public final static String TAB_PRICEALERT_PRICE_VARIATION = "ui.panel.PanelPriceAlertPriceVariation.title";		


	private JFrame mainFrame;
	
	private JPanel PanelPriceAlertPriceVariation;	


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

		///////////////////////////
		//Utils to create the frame
		///////////////////////////
		StringMessageHelper messageHelper = ServiceLocator.getInstance().getMessageHelper();

		////////////////////////
		//prepare the main frame
		////////////////////////
		JFrame frame = new JFrame();
		frame.setIconImage(ImageIconFactory.getAppImage());
		frame.setTitle("cryptocurrency alerting");
		frame.setSize(600, 400);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);

		//////////////////////////////////////////
		//add all the tabs to the main frame
		/////////////////////////////////////////
		JTabbedPane tabbedPane = new JTabbedPane();

		tabbedPane.addChangeListener(new TabModelChangeListener());	//refresh the model when enter in the tab model

		
		PanelPriceAlertPriceVariation = new PanelPriceAlertPriceVariation();

		tabbedPane.addTab(messageHelper.getFormattedMessageI18N(TAB_PRICEALERT_PRICE_VARIATION), ImageIconFactory.getForTab("priceVariationValue.png"),PanelPriceAlertPriceVariation);	


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


					if(tabName.equals(TAB_PRICEALERT_PRICE_VARIATION)){
					}


				}
			}
		}
	}
	
	
}
