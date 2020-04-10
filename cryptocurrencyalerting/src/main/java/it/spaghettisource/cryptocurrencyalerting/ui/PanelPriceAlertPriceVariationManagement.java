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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import it.spaghettisource.cryptocurrencyalerting.alert.PriceVariationGlobalMarketAlert;
import it.spaghettisource.cryptocurrencyalerting.exception.ExceptionFactory;
import it.spaghettisource.cryptocurrencyalerting.i18n.StringMessageHelper;
import it.spaghettisource.cryptocurrencyalerting.services.ServiceLocator;
import it.spaghettisource.cryptocurrencyalerting.ui.utils.FontFactory;
import it.spaghettisource.cryptocurrencyalerting.ui.utils.ImageIconFactory;

/**
 * UI to manage to manage the alert created  
 *
 * @author Alessandro D'Ottavio
 * @version 1.0
 */
public class PanelPriceAlertPriceVariationManagement extends JPanel implements ActionListener{

	private static String I18N_ROOT = "ui.panel.PanelPriceAlertPriceVariationManagement.";
	public static String I18N_TITLE = I18N_ROOT+"title";	
	
	
	private static String EVENT_DELETE_ROW = "DELETE_ROW";
	private static String EVENT_REFRESH_TABLE = "REFRESH_TABLE";	
	
	private AppSwingUIManager appSwingUIManager;
	
	private StringMessageHelper messageHelper;
	private ExceptionFactory exceptionFactory;
		
	private TableModelPriceVariationGlobalMarketAlert tableModel;
	private JTable priceAlertTable;
	
	public PanelPriceAlertPriceVariationManagement(AppSwingUIManager appSwingUIManager) {
		super();
		
		this.appSwingUIManager = appSwingUIManager;
		
		messageHelper = ServiceLocator.getInstance().getMessageHelper();
		exceptionFactory = ServiceLocator.getInstance().getExceptionFactory();
		
		setLayout(new BorderLayout());
		
		
		//NOTH set title of the panel
		JPanel northPane = createHeader(
									messageHelper.getFormattedMessageI18N(I18N_TITLE), 
									"controls.png",  
									messageHelper.getFormattedMessageI18N(I18N_ROOT+"description")); 
		
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
	    
	    tableModel = new TableModelPriceVariationGlobalMarketAlert();
		priceAlertTable = new JTable(tableModel);
		
		priceAlertTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
		//priceAlertTable.setPreferredScrollableViewportSize(new Dimension(AppSwingUIManager.FRAME_WIDTH-50, 200));
	    JScrollPane scrollPane = new JScrollPane(priceAlertTable);
	    priceAlertTable.setFillsViewportHeight(false);
	    scrollPane.setPreferredSize(new Dimension(AppSwingUIManager.FRAME_WIDTH-50, 150));
	    
		//Add the element to the layout
		c.gridx = 0;
		c.gridy = 0;
		c.insets= new Insets(0, 0, 0, 0);		
		c.fill = GridBagConstraints.BOTH;
		c.anchor = GridBagConstraints.LINE_START;	
		centerPane.add(scrollPane,c);
	    
		return centerPane;
	}

	
	private JPanel createControl() {

		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.LINE_AXIS));
		buttonPane.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
		buttonPane.add(Box.createHorizontalGlue());
		
		JButton deleteRow =  new JButton(messageHelper.getFormattedMessageI18N(I18N_ROOT+"delete"));
		deleteRow.addActionListener(this);
		deleteRow.setActionCommand(EVENT_DELETE_ROW);
		buttonPane.add(deleteRow);

		JButton refreshTable =  new JButton(messageHelper.getFormattedMessageI18N(I18N_ROOT+"refresh"));
		refreshTable.addActionListener(this);
		refreshTable.setActionCommand(EVENT_REFRESH_TABLE);
		buttonPane.add(refreshTable);
		
		return buttonPane;
	}

	
	public void fireNewAllertCreated(PriceVariationGlobalMarketAlert alert) {
		tableModel.insertRow(alert);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent event) {
				
		if(event.getActionCommand().equals(EVENT_DELETE_ROW)) {
	        if (priceAlertTable.getSelectedRow() != -1) {
	        	tableModel.removeRow(priceAlertTable.getSelectedRow());	         
	        }
		}else if(event.getActionCommand().equals(EVENT_REFRESH_TABLE)) {
	        	tableModel.refreshFullTable();	         
		}

	}




}
