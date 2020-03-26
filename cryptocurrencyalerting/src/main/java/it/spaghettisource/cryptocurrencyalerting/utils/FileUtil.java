package it.spaghettisource.cryptocurrencyalerting.utils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;

import it.spaghettisource.cryptocurrencyalerting.exception.ExceptionFactory;

public class FileUtil {


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


	public void writeStringToFile(ExceptionFactory exceptionFactory,String filePath,String fileName,String fileContent){
		
		try {
			PrintStream out = new PrintStream(new FileOutputStream(filePath+System.getProperty("file.separator")+fileName));
			out.print(fileContent);
			
		}catch (Exception cause) {
			throw exceptionFactory.getImpossibleWriteFileException(cause, fileName, filePath);
		}

	}
	
	
}
