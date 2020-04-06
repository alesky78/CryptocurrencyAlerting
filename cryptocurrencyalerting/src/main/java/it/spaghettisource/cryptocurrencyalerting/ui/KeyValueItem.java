package it.spaghettisource.cryptocurrencyalerting.ui;

/**
 * UI component used to store key values used for example in the JComboBox 
 *
 * @author Alessandro D'Ottavio
 * @version 1.0
 */
public class KeyValueItem {

	private String id;
	private String description;

	public KeyValueItem(String id, String description) {
		super();
		this.id = id;
		this.description = description;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return description;
	}
}
