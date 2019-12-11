package com.mahaboob.currencyconverter.domain;

import java.util.Map;

public class CurrencyRates {
	
	private Map<String, Double> rates;
	
	private String base;
	
	private String date;

	public Map<String, Double> getRates() {
		return rates;
	}

	public void setRates(Map<String, Double> rates) {
		this.rates = rates;
	}

	public String getBase() {
		return base;
	}

	public void setBase(String base) {
		this.base = base;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "Rates [rates=" + rates + ", base=" + base + ", date=" + date + "]";
	}

}
