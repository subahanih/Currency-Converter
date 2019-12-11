package com.mahaboob.currencyconverter.service;

import java.util.List;
import com.mahaboob.currencyconverter.domain.Currency;

public interface CurrencyService {
	
	public List<Currency> getCurrencyDetails();
	
	public Double getCalculatedCurrRate(Double amount, Double fromCurrency, Double toCurrency);

}
