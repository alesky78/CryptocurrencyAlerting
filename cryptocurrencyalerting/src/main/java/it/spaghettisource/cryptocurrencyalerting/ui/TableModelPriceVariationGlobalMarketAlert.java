package it.spaghettisource.cryptocurrencyalerting.ui;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import it.spaghettisource.cryptocurrencyalerting.action.ActionType;
import it.spaghettisource.cryptocurrencyalerting.alert.AlertType;
import it.spaghettisource.cryptocurrencyalerting.alert.PriceVariationGlobalMarketAlert;
import it.spaghettisource.cryptocurrencyalerting.repository.PriceVariationGlobalMarketAlertRepository;
import it.spaghettisource.cryptocurrencyalerting.services.ServiceLocator;

public class TableModelPriceVariationGlobalMarketAlert extends AbstractTableModel {


	private String[] columnNames = new String[] {"alertType","actionType","disable","disableAfterTrigger","enableCoolDown","coolDownMinuts"}; 	//TODO internazionalize the name of the columns
	private PriceVariationGlobalMarketAlertRepository repository;
	private List<PriceVariationGlobalMarketAlert> data;
	
	
		
	public TableModelPriceVariationGlobalMarketAlert() {
		super();
		repository = new PriceVariationGlobalMarketAlertRepository(ServiceLocator.getInstance().getExceptionFactory());
		data = repository.getAll();
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
			return AlertType.class;	
		}else if(c == 1) {
			return ActionType.class;			
		}else if(c == 2) {
			return Boolean.class;
		}else if(c == 3) {
			return Boolean.class;
		}else if(c == 4) {
			return Boolean.class;
		}else if(c == 5) {
			return Long.class;
		}
		
		return String.class;
    }
	
	
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		
		
		PriceVariationGlobalMarketAlert alert =data.get(rowIndex);
		
		if(columnIndex == 0) {
			return alert.getAlertType();	
		}else if(columnIndex == 1) {
			return alert.getActionType();			
		}else if(columnIndex == 2) {
			return alert.isDisable();	
		}else if(columnIndex == 3) {
			return alert.isDisableAfterTrigger();
		}else if(columnIndex == 4) {
			return alert.isEnableCoolDown();
		}else if(columnIndex == 5) {
			return alert.getCoolDownMinuts();
		}
		
		return null;
	}
	
	
    public boolean isCellEditable(int row, int col) {

        if (col == 2) {
            return true;
        } else if(col == 3) {
            return true;
        } else if(col == 4) {
            return true;
        } else if(col == 5) {
            return true;
        }


        
        return false;
    }


    public void setValueAt(Object value, int row, int col) {
    	
		PriceVariationGlobalMarketAlert alert = data.get(row);
		
        if (col == 2) {
        	alert.setDisable((boolean) value);
        } else if(col == 3) {
        	alert.setDisableAfterTrigger((boolean)value); 
        } else if(col == 4) {
        	alert.setEnableCoolDown((boolean)value);
        } else if(col == 5) {
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
