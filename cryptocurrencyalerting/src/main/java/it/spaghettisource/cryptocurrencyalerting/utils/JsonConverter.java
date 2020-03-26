package it.spaghettisource.cryptocurrencyalerting.utils;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import it.spaghettisource.cryptocurrencyalerting.exception.ExceptionFactory;

/**
 * Utility for Json mapping 
 * 
 * 
 * @author Alessandro D'Ottavio
 * @version 1.0
 */
public class JsonConverter {


	public String objectToJson(ExceptionFactory exceptionFactory, Object object) {

		ObjectMapper mapper = new ObjectMapper(); 

		try { 
			// covert the object as a json string 
			String jsonStr = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(object); 
			return jsonStr;
		} catch (JsonProcessingException cause) { 
			throw exceptionFactory.getJavaToJsonException(cause);
		} 

	}


	public <T> T jsonToObject(ExceptionFactory exceptionFactory, String json, Class<T> target) {

		ObjectMapper mapper = new ObjectMapper(); 

		try { 
			// covert the object as a json string 
			T object = mapper.readValue(json, target); 
			return object;
		} catch (JsonProcessingException cause) { 
			throw exceptionFactory.getJsonToJavaException(cause);
		} 

	}

	public <T> List<T> jsonAToObjectList(ExceptionFactory exceptionFactory, String json, Class<T> tClass){

		ObjectMapper mapper = new ObjectMapper();
		CollectionType listType = mapper.getTypeFactory().constructCollectionType(ArrayList.class, tClass);
		
		try { 		
			List<T> object = mapper.readValue(json, listType);
			return object;
		}catch (JsonProcessingException cause) { 
			throw exceptionFactory.getJsonToJavaException(cause);
		} 
	}	


}
