package it.spaghettisource.cryptocurrencyalerting.provider.alternativeme.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseCriptocurrencyTicker {
	
	private Quote data;
	
	public ResponseCriptocurrencyTicker() {
		super();
	}


	public Quote getData() {
		return data;
	}

	public void setData(Quote data) {
		this.data = data;
	}
	
}
