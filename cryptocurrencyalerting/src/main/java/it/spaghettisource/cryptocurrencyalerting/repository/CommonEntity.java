package it.spaghettisource.cryptocurrencyalerting.repository;

/**
 * <code>CommonEntity</code> base entity class to mantain a common id already defined in all the classes
 *
 * 
 * @author Alessandro D'Ottavio
 * @version 1.0
 *
 */
public class CommonEntity implements Entity<String> {

	private String id;
	
	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}

}
