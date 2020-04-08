package it.spaghettisource.cryptocurrencyalerting.ui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.PlainDocument;

import it.spaghettisource.cryptocurrencyalerting.action.ActionType;
import it.spaghettisource.cryptocurrencyalerting.alert.PriceVariationGlobalMarketAlert;
import it.spaghettisource.cryptocurrencyalerting.exception.ExceptionFactory;
import it.spaghettisource.cryptocurrencyalerting.i18n.StringMessageHelper;
import it.spaghettisource.cryptocurrencyalerting.provider.MarketAdapter;
import it.spaghettisource.cryptocurrencyalerting.repository.PriceVariationGlobalMarketAlertRepository;
import it.spaghettisource.cryptocurrencyalerting.services.ServiceLocator;
import it.spaghettisource.cryptocurrencyalerting.ui.utils.FilterInteger;
import it.spaghettisource.cryptocurrencyalerting.ui.utils.FilterPrice;
import it.spaghettisource.cryptocurrencyalerting.ui.utils.FontFactory;
import it.spaghettisource.cryptocurrencyalerting.ui.utils.ImageIconFactory;
import it.spaghettisource.cryptocurrencyalerting.ui.utils.KeyValueItem;

/**
 * UI to manage the creation of the Price Alert for {@link#PriceVariationGlobalMarketAlert} 
 *
 * @author Alessandro D'Ottavio
 * @version 1.0
 */
public class PanelPriceAlertPriceVariation extends JPanel implements ActionListener{

	private static String EVENT_SAVE="SAVE";

	private AppSwingUIManager appSwingUIManager;  
	
	private StringMessageHelper messageHelper;
	private MarketAdapter marketAdapter;
	private ExceptionFactory exceptionFactory;
	
	private JComboBox<KeyValueItem> action;
	private JComboBox<String> criptocurrency;
	private JComboBox<KeyValueItem> condition;
	private JComboBox<String> fiat; 
	private JTextField price;
	private JCheckBox enableCoolDown;
	private JTextField timeCoolDonw;
	private JCheckBox disableAfterTrigger;
	private JCheckBox disable;
	
	
	public PanelPriceAlertPriceVariation(AppSwingUIManager appSwingUIManager) {
		super();
		
		this.appSwingUIManager = appSwingUIManager;
		
		messageHelper = ServiceLocator.getInstance().getMessageHelper();
		marketAdapter = ServiceLocator.getInstance().getMarketAdapter();
		exceptionFactory = ServiceLocator.getInstance().getExceptionFactory();
		
		setLayout(new BorderLayout());
		
		
		//NOTH set title of the panel
		JPanel northPane = createHeader(
									messageHelper.getFormattedMessageI18N(AppSwingUIManager.TAB_PRICEALERT_PRICE_VARIATION), 
									"priceVariationValue.png",  
									messageHelper.getFormattedMessageI18N("ui.panel.PanelPriceAlertPriceVariation.description")); 
		
		add(northPane, BorderLayout.NORTH);

		//CENTER set the body
		JPanel centerPane = createBody();
		add(centerPane, BorderLayout.CENTER);
		
		//SOUTH control panel
		JPanel buttonPane = createControl();
		add(buttonPane, BorderLayout.SOUTH);
	}


	private JPanel createHeader(String title,String titleImmage,String description) {
		JPanel northPane = new JPanel();
		northPane.setLayout(new BoxLayout(northPane, BoxLayout.PAGE_AXIS));
		
		JLabel titleIcon = new JLabel(title,ImageIconFactory.getForTitle(titleImmage),JLabel.CENTER);
		JLabel descr = new JLabel(description);

		//add the font
		Font font = FontFactory.getPanelTitleFont();
		titleIcon.setFont(font);
		descr.setFont(font);		
		
		titleIcon.setAlignmentX(Component.CENTER_ALIGNMENT);
		descr.setAlignmentX(Component.CENTER_ALIGNMENT);		

		northPane.add(Box.createRigidArea(new Dimension(0,20)));
		northPane.add(titleIcon);
		northPane.add(Box.createRigidArea(new Dimension(0,10)));		
		northPane.add(descr);
		northPane.add(Box.createRigidArea(new Dimension(0,30)));		

		return northPane;
	}


	private JPanel createBody() {

		messageHelper = ServiceLocator.getInstance().getMessageHelper();
		
		//panel and layout
		JPanel centerPane = new JPanel();
		centerPane.setLayout(new GridBagLayout());
	    GridBagConstraints c = new GridBagConstraints();

		//elements		
		JLabel send = new JLabel(messageHelper.getFormattedMessageI18N("ui.panel.PanelPriceAlertPriceVariation.send"));
		
		action = new JComboBox<KeyValueItem>();
		for (ActionType value : ActionType.values()) {
			action.addItem(new KeyValueItem(value.getId(), messageHelper.getFormattedMessageI18N(value.getI18nKey())));
		}
		
		JLabel asSoonAs = new JLabel(messageHelper.getFormattedMessageI18N("ui.panel.PanelPriceAlertPriceVariation.asSoonAs"));
				
		criptocurrency = new JComboBox<String>();
		for (String actual : marketAdapter.findAllCryptocurrency()) {
			criptocurrency.addItem(actual);
		}

		
		JLabel goes = new JLabel(messageHelper.getFormattedMessageI18N("ui.panel.PanelPriceAlertPriceVariation.goes"));
		
		condition = new JComboBox<KeyValueItem>();
		condition.addItem(new KeyValueItem(PriceVariationGlobalMarketAlert.ABOVE, messageHelper.getFormattedMessageI18N(PriceVariationGlobalMarketAlert.ABOVE_I18N)));
		condition.addItem(new KeyValueItem(PriceVariationGlobalMarketAlert.BELOW, messageHelper.getFormattedMessageI18N(PriceVariationGlobalMarketAlert.BELOW_I18N)));		
		
		
		JLabel thePriceOf = new JLabel(messageHelper.getFormattedMessageI18N("ui.panel.PanelPriceAlertPriceVariation.priceOf"));
		price = new JTextField("0");
		PlainDocument doc = (PlainDocument) price.getDocument();
	    doc.setDocumentFilter(new FilterPrice());
	     

		fiat = new JComboBox<String>();
		for (String actual : marketAdapter.findAllFiat()) {
			fiat.addItem(actual);
		}
		
		//allert control elemetns
		enableCoolDown = new JCheckBox(messageHelper.getFormattedMessageI18N("ui.panel.PanelPriceAlertPriceVariation.enableCoolDown"));
		timeCoolDonw = new JTextField("60");
		PlainDocument doc2 = (PlainDocument) timeCoolDonw.getDocument();
	    doc2.setDocumentFilter(new FilterInteger());
		
		JLabel minutes = new JLabel(messageHelper.getFormattedMessageI18N("ui.panel.PanelPriceAlertPriceVariation.minutes"));		
		
		disableAfterTrigger = new JCheckBox(messageHelper.getFormattedMessageI18N("ui.panel.PanelPriceAlertPriceVariation.disableAfterTrigger"));
		disable = new JCheckBox(messageHelper.getFormattedMessageI18N("ui.panel.PanelPriceAlertPriceVariation.disable"));		
		
		//add the font
		Font font = FontFactory.getPanelBodyFont();
		send.setFont(font);
		action.setFont(font);
		asSoonAs.setFont(font);
		criptocurrency.setFont(font);
		goes.setFont(font);
		condition.setFont(font);
		thePriceOf.setFont(font);
		price.setFont(font);
		fiat.setFont(font);
		
		enableCoolDown.setFont(font);
		timeCoolDonw.setFont(font);
		minutes.setFont(font);
		disableAfterTrigger.setFont(font);
		disable.setFont(font);
		
		//Add the element to the layout
		c.gridx = 0;
		c.gridy = 0;
		c.ipadx = 50;
		c.insets= new Insets(0, 0, 0, 15);		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.LINE_START;	
		centerPane.add(send,c);
		c.gridx = 1;
		c.gridy = 0;
		centerPane.add(action,c);		
		c.gridx = 2;
		c.gridy = 0;		
		centerPane.add(asSoonAs,c);		

		c.gridx = 0;
		c.gridy = 1;
		centerPane.add(criptocurrency,c);
		c.gridx = 1;
		c.gridy = 1;
		centerPane.add(goes,c);
		c.gridx = 2;
		c.gridy = 1;		
		centerPane.add(condition,c);
		
		c.gridx = 0;
		c.gridy = 2;
		centerPane.add(thePriceOf,c);
		c.gridx = 1;
		c.gridy = 2;
		price.setColumns(7);
		centerPane.add(price,c);
		c.gridx = 2;
		c.gridy = 2;
		centerPane.add(fiat,c);			
		
		//Add the controls to the layout
		c.gridx = 0;
		c.gridy = 3;
		c.insets= new Insets(20, 0, 0, 15);
		centerPane.add(enableCoolDown,c);		
		c.gridx = 1;
		c.gridy = 3;
		timeCoolDonw.setColumns(5);
		centerPane.add(timeCoolDonw,c);		
		c.gridx = 2;
		c.gridy = 3;
		centerPane.add(minutes,c);				
		c.gridx = 0;
		c.gridy = 4;
		c.insets= new Insets(0, 0, 0, 15);		
		centerPane.add(disableAfterTrigger,c);		
		c.gridx = 1;
		c.gridy = 4;
		centerPane.add(disable,c);		
		
		
		return centerPane;
	}

	
	private JPanel createControl() {

		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.LINE_AXIS));
		buttonPane.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
		buttonPane.add(Box.createHorizontalGlue());
		JButton save =  new JButton(messageHelper.getFormattedMessageI18N("ui.panel.PanelPriceAlertPriceVariation.save"));
		save.addActionListener(this);
		save.setActionCommand(EVENT_SAVE);
		buttonPane.add(save);

		
		
		return buttonPane;
	}


	@Override
	public void actionPerformed(ActionEvent event) {
		if(event.getActionCommand().equals(EVENT_SAVE)) {
			PriceVariationGlobalMarketAlert alert = new PriceVariationGlobalMarketAlert((String)criptocurrency.getSelectedItem(), 
																						(String)fiat.getSelectedItem(), 
																						Double.valueOf(price.getText()) , 
																						((KeyValueItem)condition.getSelectedItem()).getId());
			
			alert.setActionType(ActionType.fromId(((KeyValueItem)action.getSelectedItem()).getId()));
			
			alert.setDisable(disable.isSelected());
			alert.disableAfterTrigger(disableAfterTrigger.isSelected());
			alert.setCoolDown(enableCoolDown.isSelected());
			alert.setCoolDownMinuts( Long.valueOf(timeCoolDonw.getText()));
			
			PriceVariationGlobalMarketAlertRepository alertRepository = new PriceVariationGlobalMarketAlertRepository(exceptionFactory);
			
			alertRepository.save(alert);
			
			appSwingUIManager.getPanelAlertManagement().fireNewAllertCreated(alert);
			
		}
		

	}



}
