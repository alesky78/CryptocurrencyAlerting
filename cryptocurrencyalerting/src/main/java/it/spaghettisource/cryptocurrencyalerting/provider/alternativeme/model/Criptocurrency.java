package it.spaghettisource.cryptocurrencyalerting.provider.alternativeme.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Criptocurrency {
	
	private int id;
	private String name;
	private String website_slug;
	private String symbol;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getWebsite_slug() {
		return website_slug;
	}
	public void setWebsite_slug(String website_slug) {
		this.website_slug = website_slug;
	}
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	
}
