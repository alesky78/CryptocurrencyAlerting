package it.spaghettisource.cryptocurrencyalerting.ui;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import it.spaghettisource.cryptocurrencyalerting.action.ActionType;
import it.spaghettisource.cryptocurrencyalerting.alert.AlertType;
import it.spaghettisource.cryptocurrencyalerting.alert.PriceVariationGlobalMarketAlert;
import it.spaghettisource.cryptocurrencyalerting.i18n.StringMessageHelper;
import it.spaghettisource.cryptocurrencyalerting.repository.PriceVariationGlobalMarketAlertRepository;
import it.spaghettisource.cryptocurrencyalerting.services.ServiceLocator;

public class TableModelPriceVariationGlobalMarketAlert extends AbstractTableModel {

	private static String I18N_ROOT = "ui.tablemodel.TableModelPriceVariationGlobalMarketAlert.header.";
	
	private String[] columnNames; 
	private PriceVariationGlobalMarketAlertRepository repository;
	private List<PriceVariationGlobalMarketAlert> data;
	
	private StringMessageHelper messageHelper;
	
	public TableModelPriceVariationGlobalMarketAlert() {
		super();
		repository = new PriceVariationGlobalMarketAlertRepository(ServiceLocator.getInstance().getExceptionFactory());
		data = repository.getAll();
		
		messageHelper = ServiceLocator.getInstance().getMessageHelper();
		columnNames = new String[6];
		columnNames[0] = messageHelper.getFormattedMessageI18N(I18N_ROOT+"actionType");
		columnNames[1] = messageHelper.getFormattedMessageI18N(I18N_ROOT+"diasble");
		columnNames[2] = messageHelper.getFormattedMessageI18N(I18N_ROOT+"diasbleAfterTrigger");
		columnNames[3] = messageHelper.getFormattedMessageI18N(I18N_ROOT+"coolDonw");
		columnNames[4] = messageHelper.getFormattedMessageI18N(I18N_ROOT+"coolDonwTime");
		columnNames[5] = messageHelper.getFormattedMessageI18N(I18N_ROOT+"description");
		
		
	}


	@Override
    public String getColumnName(int col) {
        return columnNames[col];
    }
	
	
	@Override
	public int getRowCount() {
		return data.size();
	}

	
	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	
	@Override
    public Class getColumnClass(int c) {
		if(c == 0) {
			return ActionType.class;			
		}else if(c == 1) {
			return Boolean.class;
		}else if(c == 2) {
			return Boolean.class;
		}else if(c == 3) {
			return Boolean.class;
		}else if(c == 4) {
			return Long.class;
		}else if(c == 5) {
			return String.class;
		}
		
		return String.class;
    }
	
	
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		
		
		PriceVariationGlobalMarketAlert alert = data.get(rowIndex);
		
		if(columnIndex == 0) {
			return messageHelper.getFormattedMessageI18N(alert.getActionType().getI18nKey());			
		}else if(columnIndex == 1) {
			return alert.isDisable();	
		}else if(columnIndex == 2) {
			return alert.isDisableAfterTrigger();
		}else if(columnIndex == 3) {
			return alert.isEnableCoolDown();
		}else if(columnIndex == 4) {
			return alert.getCoolDownMinuts();
		}else if(columnIndex == 5) {
			return alert.createShortMessage();
		}
		
		return null;
	}
	
	
    public boolean isCellEditable(int row, int col) {

        if (col == 1) {
            return true;
        } else if(col == 2) {
            return true;
        } else if(col == 3) {
            return true;
        } else if(col == 4) {
            return true;
        }


        
        return false;
    }


    public void setValueAt(Object value, int row, int col) {
    	
		PriceVariationGlobalMarketAlert alert = data.get(row);
		
        if (col == 1) {
        	alert.setDisable((boolean) value);
        } else if(col == 2) {
        	alert.setDisableAfterTrigger((boolean)value); 
        } else if(col == 3) {
        	alert.setEnableCoolDown((boolean)value);
        } else if(col == 4) {
        	alert.setCoolDownMinuts((long) value);
        }

		repository.update(alert);	
        fireTableCellUpdated(row, col);
    }	
    
    
    public void insertRow(PriceVariationGlobalMarketAlert alert) {
    	int row = data.size();
    	data.add(alert);
        fireTableRowsInserted(row, row);
        
    }
    
    
    public void removeRow(int row) {
    	PriceVariationGlobalMarketAlert toDelete = data.remove(row);
    	repository.delete(toDelete);
        fireTableRowsDeleted(row, row);
    }
    
    
    public void refreshFullTable() {
    	data = repository.getAll();
    	fireTableDataChanged();
    }

}
