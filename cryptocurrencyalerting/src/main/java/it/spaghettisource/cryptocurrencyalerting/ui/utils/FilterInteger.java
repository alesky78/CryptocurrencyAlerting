package it.spaghettisource.cryptocurrencyalerting.ui.utils;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.DocumentFilter;

/**
/**
 * Utility used in the JText to accept only string that can rapresent valide price 
 * 
 * 
 * @author Alessandro D'Ottavio
 * @version 1.0
 */
public class FilterInteger extends DocumentFilter {

	@Override
	public void insertString(FilterBypass fb, int offset, String string,AttributeSet attr) throws BadLocationException {

		Document doc = fb.getDocument();
		StringBuilder sb = new StringBuilder();
		sb.append(doc.getText(0, doc.getLength()));
		sb.insert(offset, string);

		if (isValidInteger(sb.toString())) {
			super.insertString(fb, offset, string, attr);
		} else {
			// warn the user and don't allow the insert
		}
	}

	@Override
	public void replace(FilterBypass fb, int offset, int length, String text,AttributeSet attrs) throws BadLocationException {

		Document doc = fb.getDocument();
		StringBuilder sb = new StringBuilder();
		sb.append(doc.getText(0, doc.getLength()));
		sb.replace(offset, offset + length, text);

		if (offset == 1 && length == 0 && doc.getText(0, doc.getLength()).equals("0")) { //if insert a value after first 0 replace the 0 whit this value
			if(isValidInteger(text)) {
				fb.remove(0, 1);
				fb.insertString(0, text, null);				
			}
		}else if (isValidInteger(sb.toString())) {
			super.replace(fb, offset, length, text, attrs);
		} else {
			// warn the user and don't allow the insert
		}

	}

	@Override
	public void remove(FilterBypass fb, int offset, int length)throws BadLocationException {
		Document doc = fb.getDocument();
		StringBuilder sb = new StringBuilder();
		sb.append(doc.getText(0, doc.getLength()));
		sb.delete(offset, offset + length);

		if(sb.length()==0){	//if remove all set default value
			fb.remove(offset, length);
			fb.insertString(0, "0", null);
		}else if(isValidInteger(sb.toString())) {
			super.remove(fb, offset, length);
		} else {
			// warn the user and don't allow the insert
		}

	}
	
	

	
	private boolean isValidInteger(String text) {
		try {
			Integer.parseInt(text);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
	
}
