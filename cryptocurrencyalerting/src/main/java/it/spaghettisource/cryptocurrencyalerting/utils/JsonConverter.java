package it.spaghettisource.cryptocurrencyalerting.utils;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
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
		//mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

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

	
	public <T> T jsonToObject(ExceptionFactory exceptionFactory, String json, Class<T> target,StdDeserializer deserialized,Class deserializedClass) {

		ObjectMapper mapper = new ObjectMapper(); 
		
		SimpleModule module =new SimpleModule("CustomCarDeserializer", new Version(1, 0, 0, null, null, null));
		module.addDeserializer(deserializedClass, deserialized);
		mapper.registerModule(module);		

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
