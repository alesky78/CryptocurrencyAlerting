package it.spaghettisource.cryptocurrencyalerting.provider.coinmarketcap.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseCriptocurrencyMap {

	private Status status;
	private List<Criptocurrency> data;
	
	public ResponseCriptocurrencyMap() {
		super();
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public List<Criptocurrency> getData() {
		return data;
	}

	public void setData(List<Criptocurrency> data) {
		this.data = data;
	}
	
}
