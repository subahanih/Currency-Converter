package com.mahaboob.currencyconverter.domain;

public class Currency {

	private String currencyCode;
	
	private Double currencyRate;

	private String currencyBase;

	private String currencyDate;

	public Currency() {
	}

	public Currency(String currencyCode, Double currencyRate, String currencyBase, String currencyDate) {
		this.currencyCode = currencyCode;
		this.currencyRate = currencyRate;
		this.currencyBase = currencyBase;
		this.currencyDate = currencyDate;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public Double getCurrencyRate() {
		return currencyRate;
	}

	public void setCurrencyRate(Double currencyRate) {
		this.currencyRate = currencyRate;
	}

	public String getCurrencyBase() {
		return currencyBase;
	}

	public void setCurrencyBase(String currencyBase) {
		this.currencyBase = currencyBase;
	}

	public String getCurrencyDate() {
		return currencyDate;
	}

	public void setCurrencyDate(String currencyDate) {
		this.currencyDate = currencyDate;
	}
	
}