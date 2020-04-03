package it.spaghettisource.cryptocurrencyalerting.ui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import it.spaghettisource.cryptocurrencyalerting.i18n.StringMessageHelper;
import it.spaghettisource.cryptocurrencyalerting.services.ServiceLocator;
import it.spaghettisource.cryptocurrencyalerting.utils.FontFactory;
import it.spaghettisource.cryptocurrencyalerting.utils.ImageIconFactory;

/**
 * UI to manage the creation of the Price Alert for price variation
 *
 * @author Alessandro D'Ottavio
 * @version 1.0
 */
public class PanelPriceAlertPriceVariation extends JPanel {

	public PanelPriceAlertPriceVariation() {
		super();
		setLayout(new BorderLayout());
		StringMessageHelper messageHelper = ServiceLocator.getInstance().getMessageHelper();
		
		//NOTH set title of the panel
		JPanel northPane = createHeader(
									messageHelper.getFormattedMessageI18N(AppSwingUIManager.TAB_PRICEALERT_PRICE_VARIATION), 
									"priceVariationValue.png",  
									messageHelper.getFormattedMessageI18N("ui.panel.PanelPriceAlertPriceVariation.description")); 
		
		add(northPane, BorderLayout.NORTH);

		//CENTER set the body
		JPanel centerPane = createBody();

		
		add(centerPane, BorderLayout.CENTER);
		
		//SOUTH set the controls here
		//Lay out the buttons from left to right.
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.LINE_AXIS));
		buttonPane.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
		buttonPane.add(Box.createHorizontalGlue());
		buttonPane.add(new JButton("bottone 1"));
		buttonPane.add(Box.createRigidArea(new Dimension(10, 0)));
		buttonPane.add(new JButton("bottone 2"));

		add(buttonPane, BorderLayout.SOUTH);
	}


	private JPanel createHeader(String title,String titleImmage,String description) {
		JPanel northPane = new JPanel();
		northPane.setLayout(new BoxLayout(northPane, BoxLayout.PAGE_AXIS));
		
		JLabel titleIcon = new JLabel(title,ImageIconFactory.getForTitle(titleImmage),JLabel.CENTER);
		JLabel descr = new JLabel(description);
		
		titleIcon.setFont(FontFactory.getPanelTitleFont());
		descr.setFont(FontFactory.getPanelTitleFont());		
		
		titleIcon.setAlignmentX(Component.CENTER_ALIGNMENT);
		descr.setAlignmentX(Component.CENTER_ALIGNMENT);		

		northPane.add(Box.createRigidArea(new Dimension(0,20)));
		northPane.add(titleIcon);
		northPane.add(Box.createRigidArea(new Dimension(0,10)));		
		northPane.add(descr);
		northPane.add(Box.createRigidArea(new Dimension(0,20)));		

		return northPane;
	}


	private JPanel createBody() {

		FlowLayout flowLayout = new FlowLayout();
		JPanel centerPane = new JPanel();
		centerPane.setLayout(flowLayout);
		
		
		//Add buttons to experiment with Grid Layout
		centerPane.add(new JButton("5"));
		centerPane.add(new JLabel("testo"));
		centerPane.add(new JLabel(ImageIconFactory.getForTitle("priceVariationValue.png")));
		centerPane.add(new JLabel("testo"));
		centerPane.add(new JButton("5"));
		
		
		
		return centerPane;
	}



}
