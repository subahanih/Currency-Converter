package com.mahaboob.currencyconverter.serviceimpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import com.mahaboob.currencyconverter.domain.Currency;
import com.mahaboob.currencyconverter.domain.CurrencyRates;
import com.mahaboob.currencyconverter.service.CurrencyService;

@Service
public class CurrencyServiceImpl implements CurrencyService {

	@Override
	@Cacheable(value = "currencyRate")
	@PreAuthorize("hasAuthority('admin-read-currency') or hasAuthority('user-read-currency')")
	public List<Currency> getCurrencyDetails() {
		System.out.println("Inside getCurrencyDetails: ");
		List<Currency> listCurrency = new ArrayList<>();
		try {
			CurrencyRates curr = new ObjectMapper().readValue(callURL("https://api.exchangeratesapi.io/latest"),
					CurrencyRates.class);
			System.out.println("\n\nJSON Object: " + curr);
			System.out.println("Base: " + curr.getBase());
			System.out.println("Date: " + curr.getDate());
			System.out.println("Rates: " + curr.getRates());
			// This line will add base(EUR) value(always 1) to the list
			listCurrency.add(new Currency(curr.getBase(), 1.00, curr.getBase(), curr.getDate()));
			curr.getRates().forEach((k, v) -> {
				Currency currency = new Currency();
				currency.setCurrencyBase(curr.getBase());
				currency.setCurrencyDate(curr.getDate());
				currency.setCurrencyCode(k);
				currency.setCurrencyRate(v);
				listCurrency.add(currency);
			});
		} catch (JsonParseException jp) {
			jp.printStackTrace();
		} catch (JsonMappingException jp) {
			jp.printStackTrace();
		} catch (IOException jp) {
			jp.printStackTrace();
		}
		return listCurrency;
	}

	@Override
	@CacheEvict(value = "currencyRate", allEntries = true)
	public Double getCalculatedCurrRate(Double amount, Double fromCurrency, Double toCurrency) {
		DecimalFormat decimalFormat = new DecimalFormat("##.#####");
		return Double.valueOf(decimalFormat.format((toCurrency / fromCurrency) * amount));
	}

	private String callURL(String myURL) {
		System.out.println("Requested URL:" + myURL);
		StringBuilder sb = new StringBuilder();
		URLConnection urlConn = null;
		InputStreamReader in = null;
		try {
			URL url = new URL(myURL);
			urlConn = url.openConnection();
			if (urlConn != null)
				urlConn.setReadTimeout(60 * 1000);
			if (urlConn != null && urlConn.getInputStream() != null) {
				in = new InputStreamReader(urlConn.getInputStream(), Charset.defaultCharset());
				BufferedReader bufferedReader = new BufferedReader(in);
				if (bufferedReader != null) {
					int cp;
					while ((cp = bufferedReader.read()) != -1) {
						sb.append((char) cp);
					}
					bufferedReader.close();
				}
			}
			in.close();
		} catch (Exception e) {
			throw new RuntimeException("Exception while calling URL:" + myURL, e);
		}

		return sb.toString();
	}

}
