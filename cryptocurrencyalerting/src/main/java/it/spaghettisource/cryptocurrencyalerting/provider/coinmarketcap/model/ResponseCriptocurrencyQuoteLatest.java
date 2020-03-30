package it.spaghettisource.cryptocurrencyalerting.provider.coinmarketcap.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseCriptocurrencyQuoteLatest {

	private Status status;	
	private Quote data;
	
	public ResponseCriptocurrencyQuoteLatest() {
		super();
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Quote getData() {
		return data;
	}

	public void setData(Quote data) {
		this.data = data;
	}
	
}
