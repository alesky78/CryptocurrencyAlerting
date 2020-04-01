package it.spaghettisource.cryptocurrencyalerting.repository;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import it.spaghettisource.cryptocurrencyalerting.exception.BaseException;
import it.spaghettisource.cryptocurrencyalerting.exception.ExceptionFactory;
import it.spaghettisource.cryptocurrencyalerting.utils.FileUtil;
import it.spaghettisource.cryptocurrencyalerting.utils.JsonConverter;

/**
 * Implementation of the basic Repository using file in filesystem. 
 * 
 * @author Alessandro D'Ottavio
 *
 * @param <T> Class of the Entity
 * @param <PK> Pk used by the entity
 * 
 * @author Alessandro D'Ottavio
 * @version 1.0
 */
public abstract class GenericFileRepository<T extends Entity<PK>, PK extends Serializable> implements Repository<T, PK> {

	protected String filePath;
	protected String fileName;
	protected ExceptionFactory exceptionFactory;

	private List<T> readAllFromFileSystem(){
		FileUtil fileUtil = new FileUtil();
		
		if(!fileUtil.isFileExsist(filePath, fileName)) {
			return new ArrayList<T>();
		}
		
		String json = fileUtil.readFileToString(exceptionFactory, filePath, fileName);
		JsonConverter converter = new JsonConverter();
		Class<T> targetClass = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		List<T> data = converter.jsonAToObjectList(exceptionFactory, json,targetClass);
		return data;
	}	

	private void saveAllToFileSystem(List<T> data){
		JsonConverter converter = new JsonConverter();		
		String json = converter.objectToJson(exceptionFactory, data);
		FileUtil fileUtil = new FileUtil();
		fileUtil.writeStringToFile(exceptionFactory, filePath, fileName, json);
	}
	
	private boolean deleteFileFromFileSystem() {
		FileUtil fileUtil = new FileUtil();
		return fileUtil.deleteFile(filePath, fileName);
	}
	
	public abstract PK generateKey();
	
	
	@Override
	public T get(PK id) {
		List<T> data = readAllFromFileSystem();
		for (T entity : data) {
			if(entity.getId().equals(id)) {
				return entity;
			}
		}
		
		return null;
	}


	@Override
	public List<T> getAll() {
		return readAllFromFileSystem();
	}


	@Override
	public T save(T entity) throws BaseException {
		List<T> data = readAllFromFileSystem();
		
		if(entity.getId()==null) {
			entity.setId(generateKey());
		}else {
			if(get(entity.getId())!=null) {
				throw exceptionFactory.getDuplicatePrimariKey(entity.getId());
			}
		}
		
		data.add(entity);
		saveAllToFileSystem(data);
		return entity;
		
	}
	
    public void update(T entity) throws BaseException{
    	if(entity.getId()==null) {
    		throw exceptionFactory.getEntityNotExsist();
    	}else {
        	T oldEntity = get(entity.getId());
        	if(oldEntity==null) {
    			throw exceptionFactory.getEntityNotExsist();    		
        	}else {
        		delete(oldEntity);
        		save(entity);
        	}    		
    	}
    }


	@Override
	public void delete(T entity) {
		List<T> data = readAllFromFileSystem();
		
		T toRemove = null;
		for (T actual : data) {
			if(actual.getId().equals(entity.getId()) ) {
				toRemove = actual;
			}
		}
		
		data.remove(toRemove);
		saveAllToFileSystem(data);	
	}
	
	@Override	
	public void deleteAll() {
		deleteFileFromFileSystem();
	}
	
}
