package it.spaghettisource.cryptocurrencyalerting.repository;

import java.io.Serializable;
import java.util.List;

import it.spaghettisource.cryptocurrencyalerting.exception.BaseException;


/**
 * Interface that represents all the basic operation for the classic Repostiory.
 *
 * @param <T> object's type, of the repository
 * @param <PK> Pk used by the entity
 * 
 * @author 		D'Ottavio Alessandro
 * @version 1.0
 */
public interface Repository<T extends Entity<PK>, PK extends Serializable> {
    
  
    /**
     * Retrieve an persisted object using the given id as primary key.
     *
     * Returns null if not found.
     *
     * @param id object's primary key
     * @return object
     */
    public T get(PK id);    

    /**
     * Retrieve all persisted objects.
     * 
     * Return an empty list if not found
     * 
     * @return list of objects
     */
   	public List<T> getAll();
    
    
   	/**
   	 * insert the new object
   	 * 
   	 * @param entity
   	 * @return
   	 * @throws BaseException if the object already exsist
   	 */
    public T save(T entity) throws BaseException;


    /**
     * update an object that already exsist
     *
     * @param objects objects
   	 * @throws BaseException if there is not entity to update
     */    
    public void update(T entity) throws BaseException;
    
    
    /**
     * Delete the specific object
     */    
    public void delete(T entity);    
    
    
    /**
     * Delete the specific object
     */    
    public void deleteAll();    
    
}