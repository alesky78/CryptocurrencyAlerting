package it.spaghettisource.cryptocurrencyalerting.provider.alternativeme.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseCryptocurrencyListing {

	private List<Criptocurrency> data;
	
	public ResponseCryptocurrencyListing() {
		super();
	}


	public List<Criptocurrency> getData() {
		return data;
	}

	public void setData(List<Criptocurrency> data) {
		this.data = data;
	}
	
}
