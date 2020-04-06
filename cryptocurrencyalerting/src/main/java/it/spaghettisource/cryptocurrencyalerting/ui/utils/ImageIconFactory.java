package it.spaghettisource.cryptocurrencyalerting.ui.utils;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Utility used to create the icons in the UI 
 * 
 * 
 * @author Alessandro D'Ottavio
 * @version 1.0
 */
public class ImageIconFactory {

	static Log log = LogFactory.getLog(ImageIconFactory.class.getName());

	public final static String ICON_LOCATION = "/icon/";

	public final static int ICON_SIZE_TAB = 25;
	public final static int ICON_SIZE_BUTTON = 25;
	public final static int ICON_SIZE_LABEL = 40;
	public final static int ICON_SIZE_TITLE = 20;		
	public final static int ICON_SIZE_FRAME = 5;
	public final static int ICON_SIZE_TABLE_CELL = 10;

	private ImageIconFactory(){
	}


	private static Image getImageByNameAndSize(String name,int size) {

		try {
			BufferedImage bufferedImage;
			bufferedImage = ImageIO.read(ImageIconFactory.class.getResourceAsStream(ICON_LOCATION+name));
			return bufferedImage.getScaledInstance(size, size,  java.awt.Image.SCALE_SMOOTH);
		} catch (IOException e) {
			String message = "error loading the immange "+name+" for:"+e.getMessage();
			log.error(message,e );
			throw new RuntimeException(message,e);
		}

	}

	private static ImageIcon getImageIconByNameAndSize(String name,int size) {
		return new ImageIcon(getImageByNameAndSize(name,size));
	}


	public static ImageIcon getForTab(String name) {
		return getImageIconByNameAndSize(name, ICON_SIZE_TAB);
	}

	public static ImageIcon getForButton(String name) {
		return getImageIconByNameAndSize(name, ICON_SIZE_BUTTON);
	}

	public static ImageIcon getForLabel(String name) {
		return getImageIconByNameAndSize(name, ICON_SIZE_LABEL);
	}

	public static ImageIcon getForTitle(String name) {
		return getImageIconByNameAndSize(name, ICON_SIZE_TITLE);
	}	
	
	public static ImageIcon getForTableCell(String name) {
		return getImageIconByNameAndSize(name, ICON_SIZE_TABLE_CELL);
	}

	public static Image getAppImage() {
		try {
			return ImageIO.read(ImageIconFactory.class.getResourceAsStream(ICON_LOCATION+"application.png"));
		} catch (IOException e) {
			log.error("error loading the app image for:"+e.getMessage(),e );
			throw new RuntimeException(e);
		}

	}

}
