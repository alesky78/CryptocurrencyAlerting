package it.spaghettisource.cryptocurrencyalerting.utils;

import java.awt.Font;

/**
 * Utility used to create and centralize the font in the application 
 * 
 * 
 * @author Alessandro D'Ottavio
 * @version 1.0
 */
public class FontFactory {

	private FontFactory() {
		super();
	}
	
	public static Font getPanelTitleFont() {
		return new Font("monospace", Font.BOLD, 15);
	}

	public static Font getPanelBodyFont() {
		return new Font("monospace", Font.PLAIN, 12);
	}
	

}
