package it.spaghettisource.cryptocurrencyalerting.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import it.spaghettisource.cryptocurrencyalerting.exception.ExceptionFactory;

public class JsonConverter {

	
	public String objectToJson(ExceptionFactory exceptionFactory, Object object) {
		 
        ObjectMapper mapper = new ObjectMapper(); 
  
        try { 
  
            // covert the object as a json string 
            String jsonStr = mapper.writeValueAsString(object); 
   
            return jsonStr;
        } 
  
        catch (JsonProcessingException cause) { 
        	throw exceptionFactory.getJavaToJsonException(cause);
        } 
        
	}
	

	public <T> T jsonToObject(ExceptionFactory exceptionFactory, String json, Class<T> target) {
		 
        ObjectMapper mapper = new ObjectMapper(); 
  
        try { 
  
            // covert the object as a json string 
            T object = mapper.readValue(json, target); 
   
            return object;
        } 
  
        catch (JsonProcessingException cause) { 
        	throw exceptionFactory.getJsonToJavaException(cause);
        } 
        
	}
	
	
}
