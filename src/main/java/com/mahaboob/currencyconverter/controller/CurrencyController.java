package com.mahaboob.currencyconverter.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.mahaboob.currencyconverter.domain.Currency;
import com.mahaboob.currencyconverter.serviceimpl.CurrencyServiceImpl;

@RestController
@RequestMapping("/secured")
public class CurrencyController {

	@Autowired
	private CurrencyServiceImpl currencyServiceImpl;

	/*
	 * This method will load the currency details to combo boxes from
	 * https://api.exchangeratesapi.io/latest
	 */
	@RequestMapping(value = "/getAllCurrencyDetails", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value = HttpStatus.OK)
	public @ResponseBody List<Currency> getCurrencyDetails() {
		return currencyServiceImpl.getCurrencyDetails();
	}

	/* This method will return calculated currency rate */
	@RequestMapping(value = "/getCalculatedCurrRate", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value = HttpStatus.OK)
	public @ResponseBody Double getCalculatedCurrRate(Double amount, Double fromCurrency, Double toCurrency) {
		return currencyServiceImpl.getCalculatedCurrRate(amount, fromCurrency, toCurrency);
	}

}
