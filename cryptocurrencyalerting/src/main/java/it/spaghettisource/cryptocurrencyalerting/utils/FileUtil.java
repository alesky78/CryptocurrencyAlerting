package it.spaghettisource.cryptocurrencyalerting.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;

import it.spaghettisource.cryptocurrencyalerting.exception.ExceptionFactory;

/**
 * Utility for File operations 
 * 
 * 
 * @author Alessandro D'Ottavio
 * @version 1.0
 */
public class FileUtil {


	
	public boolean isFileExsist(String filePath,String fileName) {		
		File file = new File(filePath+System.getProperty("file.separator")+fileName);
		return file.exists();
	}
	
	public boolean deleteFile(String filePath,String fileName) {
		File file = new File(filePath+System.getProperty("file.separator")+fileName);
		return file.delete();
	}
	
	
	public String readFileToString(ExceptionFactory exceptionFactory,String filePath,String fileName){
		
		try {
			InputStream is = new FileInputStream(filePath+System.getProperty("file.separator")+fileName); 
			BufferedReader buf = new BufferedReader(new InputStreamReader(is)); 
			String line = buf.readLine(); 
			StringBuilder sb = new StringBuilder(); 
			
			while(line != null){ 
					sb.append(line).append("\n"); 
					line = buf.readLine(); 
			} 
			
			buf.close();
			
			return sb.toString();			
		}catch (Exception cause) {
			throw exceptionFactory.getImpossibleReadFileException(cause, fileName, filePath);
		}

	}

	public InputStream readFileToInputStream(ExceptionFactory exceptionFactory,String filePath,String fileName){
		
		try {
			return new FileInputStream(filePath+System.getProperty("file.separator")+fileName); 		
		}catch (Exception cause) {
			throw exceptionFactory.getImpossibleReadFileException(cause, fileName, filePath);
		}

	}

	public void writeStringToFile(ExceptionFactory exceptionFactory,String filePath,String fileName,String fileContent){
		
		try {
			PrintStream out = new PrintStream(new FileOutputStream(filePath+System.getProperty("file.separator")+fileName));
			out.print(fileContent);
			out.close();
			
		}catch (Exception cause) {
			throw exceptionFactory.getImpossibleWriteFileException(cause, fileName, filePath);
		}

	}
	
	
}
