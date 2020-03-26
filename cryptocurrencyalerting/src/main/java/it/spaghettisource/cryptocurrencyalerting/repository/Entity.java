package it.spaghettisource.cryptocurrencyalerting.repository;

import java.io.Serializable;

/**
 * Interface marks class which can be persisted.
 * <p>
 * All the class that implement this interface have to store the PK in the property id    
 * <p>
 * The primary key is generated automatically by the repostiory implementation
 * 
 * @param <PK> type of primary key, it must be serializable
 *
 * @author 		D'Ottavio Alessandro
 * @version 1.0 
 */
public interface Entity<PK extends Serializable> extends Serializable {
    
    /**
     * Retrieve the primary key object
     *
     * @return primary key
     */
	PK getId();

    /**
     * Set the  primary key object.
     *
     * @param id primary key
     */
    void setId(PK id);
}
