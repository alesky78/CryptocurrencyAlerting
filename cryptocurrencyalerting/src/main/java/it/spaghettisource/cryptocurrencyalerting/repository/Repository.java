package it.spaghettisource.cryptocurrencyalerting.repository;

import java.io.Serializable;
import java.util.List;


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
     * Save all changes made to objects or insert the objects if they doesn't exists.
     *
     * @param objects objects
     */    
    public T save(T entity);

    
    /**
     * Delete the specific object
     */    
    public void delete(T entity);    
    
    
    /**
     * Delete the specific object
     */    
    public void deleteAll();    
    
}