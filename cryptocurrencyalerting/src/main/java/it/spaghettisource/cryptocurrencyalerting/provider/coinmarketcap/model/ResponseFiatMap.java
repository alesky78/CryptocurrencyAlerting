package it.spaghettisource.cryptocurrencyalerting.provider.coinmarketcap.model;

import java.util.List;


public class ResponseFiatMap {

	private Status status;
	private List<Fiat> data;
	
	public ResponseFiatMap() {
		super();
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public List<Fiat> getData() {
		return data;
	}

	public void setData(List<Fiat> data) {
		this.data = data;
	}
	
}
