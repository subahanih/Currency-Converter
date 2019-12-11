package com.mahaboob.currencyconverter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.annotation.WebServlet;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONException;
import com.mahaboob.currencyconverter.domain.Currency;
import com.mahaboob.currencyconverter.helper.CurrencyRateHelper;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.data.HasValue;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.UserError;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

/**
 * This UI is the application entry point. A UI may either represent a browser
 * window (or tab) or some part of an HTML page where a Vaadin application is
 * embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is
 * intended to be overridden to add component to the user interface and
 * initialize non-component functionality.
 */

@SpringUI
@Title("Currency Converter")
@Theme("currency-converter")
@SuppressWarnings("serial")
public class CurrencyConverterUI extends UI {

	private TextField tfAmountFrom, tfConvertedAmount;
	private ComboBox<Currency> cbCurrencyFrom, cbCurrencyTo;
	private Label lblLiveExchangeRate, lblTime;
	private Double currencyFrom, currencyTo;
	private String currencyDate;

	public CurrencyConverterUI() {
		loadCurrency();
	}

	@Override
	protected void init(VaadinRequest vaadinRequest) {
		Image imgCurrency = new Image(null, new ThemeResource("images/currency.png"));
		imgCurrency.setWidth("100px");
		imgCurrency.setHeight("80px");
		Label lblHeading = new Label(
				"<b><p align=\"middle\"><font size=\"5px\"color=\"#ffffff\">Nosto Currency Converter</p></b>",
				ContentMode.HTML);
		lblLiveExchangeRate = new Label();
		lblLiveExchangeRate.setCaption("With Live Exchange Rate");
		tfAmountFrom = new TextField("Amount");
		tfAmountFrom.setWidth("180px");
		tfAmountFrom.setMaxLength(15);
		tfAmountFrom.setRequiredIndicatorVisible(true);
		cbCurrencyFrom = new ComboBox<Currency>("Currency From");
		cbCurrencyFrom.setWidth("150px");
		cbCurrencyFrom.setItemCaptionGenerator(Currency::getCurrencyCode);
		// Currency loaded from http://localhost:8080/secured/getAllCurrencyDetails
		// as per requirement again getAllCurrencyDetails fetched from
		// https://api.exchangeratesapi.io/latest
		cbCurrencyFrom.setItems(loadCurrency());
		cbCurrencyFrom.setRequiredIndicatorVisible(true);
		cbCurrencyFrom.addValueChangeListener((HasValue.ValueChangeEvent<Currency> event) -> {
			if (event.getValue() != null) {
				Currency currency = event.getValue();
				currencyFrom = currency.getCurrencyRate();
			}
		});
		HorizontalLayout hlCurrencyFrom = new HorizontalLayout();
		hlCurrencyFrom.addComponents(tfAmountFrom, cbCurrencyFrom);
		tfConvertedAmount = new TextField("Converted Amount");
		tfConvertedAmount.setEnabled(false);
		tfConvertedAmount.setWidth("180px");
		cbCurrencyTo = new ComboBox<Currency>("Currency To");
		cbCurrencyTo.setWidth("150px");
		cbCurrencyTo.setRequiredIndicatorVisible(true);
		cbCurrencyTo.setItemCaptionGenerator(Currency::getCurrencyCode);
		// Currency loaded from http://localhost:8080/secured/getAllCurrencyDetails
		// as per requirement again getAllCurrencyDetails fetched from
		// https://api.exchangeratesapi.io/latest
		cbCurrencyTo.setItems(loadCurrency());
		cbCurrencyTo.addValueChangeListener((HasValue.ValueChangeEvent<Currency> event) -> {
			if (event.getValue() != null) {
				Currency currency = event.getValue();
				currencyTo = currency.getCurrencyRate();
				currencyDate = currency.getCurrencyDate();
			}
		});
		HorizontalLayout hlCurrencyTo = new HorizontalLayout();
		hlCurrencyTo.addComponents(tfConvertedAmount, cbCurrencyTo);
		Button btnConvert = new Button("Convert");
		btnConvert.setWidth("230px");
		btnConvert.addClickListener(e -> {
			if (validateDetails()) {
				convertCurrency();
			}
		});
		Button btnReset = new Button("Reset");
		btnReset.setWidth("100px");
		btnReset.addClickListener(e -> {
			resetFields();
		});
		Label lblMessage = new Label(
				"<p align=\"middle\"><font size=\"3px\"color=\"#0251ad\">Nosto Recruitment Process - Java Assignment(Currency Converter)</p>",
				ContentMode.HTML);
		HorizontalLayout hlButton = new HorizontalLayout();
		hlButton.addComponents(btnConvert, btnReset);

		VerticalLayout vlImgHeading = new VerticalLayout();
		vlImgHeading.setWidth("500px");
		vlImgHeading.setHeight("130px");
		vlImgHeading.setSpacing(false);
		vlImgHeading.setMargin(false);
		vlImgHeading.addComponents(imgCurrency, lblHeading);
		vlImgHeading.setStyleName("heading-background");
		vlImgHeading.setComponentAlignment(imgCurrency, Alignment.MIDDLE_CENTER);
		vlImgHeading.setComponentAlignment(lblHeading, Alignment.MIDDLE_CENTER);
		lblTime = new Label();
		lblTime.setCaption("");
		VerticalLayout vlTime = new VerticalLayout();
		vlTime.addComponent(lblTime);
		vlTime.setMargin(false);
		vlTime.setHeight("10px");
		VerticalLayout vlComponent = new VerticalLayout();
		vlComponent.setWidth("500px");
		vlComponent.setMargin(true);
		vlComponent.setStyleName("component-background");
		vlComponent.addComponents(lblLiveExchangeRate, hlCurrencyFrom, hlCurrencyTo, hlButton, vlTime);
		vlComponent.setComponentAlignment(lblLiveExchangeRate, Alignment.MIDDLE_CENTER);
		vlComponent.setComponentAlignment(hlCurrencyFrom, Alignment.MIDDLE_CENTER);
		vlComponent.setComponentAlignment(hlCurrencyTo, Alignment.MIDDLE_CENTER);
		vlComponent.setComponentAlignment(hlButton, Alignment.MIDDLE_CENTER);
		vlComponent.setComponentAlignment(vlTime, Alignment.MIDDLE_CENTER);
		VerticalLayout vlBottomMsg = new VerticalLayout();
		vlBottomMsg.setWidth("50%");
		vlBottomMsg.addComponents(lblMessage);
		vlBottomMsg.setComponentAlignment(lblMessage, Alignment.BOTTOM_CENTER);

		VerticalLayout vlRoot = new VerticalLayout();
		vlRoot.setSizeFull();
		vlRoot.setSpacing(false);
		vlRoot.setMargin(false);
		vlRoot.addComponents(vlImgHeading, vlComponent, vlBottomMsg);
		vlRoot.setComponentAlignment(vlImgHeading, Alignment.BOTTOM_CENTER);
		vlRoot.setExpandRatio(vlImgHeading, 1);
		vlRoot.setComponentAlignment(vlComponent, Alignment.TOP_CENTER);
		vlRoot.setComponentAlignment(vlBottomMsg, Alignment.BOTTOM_CENTER);
		setContent(vlRoot);
	}

	// This method will load Access Token to run the transaction.
	private String loadAccessToken() {
		String accessToken = null;
		try {
			accessToken = CurrencyRateHelper.getAccessToken();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return accessToken;
	}

	// This method will be used to load all currency to ComboBox.
	private List<Currency> loadCurrency() {
		System.out.println("Inside loadCurrency");
		List<Currency> listCurrency = new ArrayList<Currency>();
		Currency currency = null;
		try {
			Currency[] currObjMapper = new ObjectMapper()
					.readValue(CurrencyRateHelper.callGetAllCurrencyDetails(loadAccessToken()), Currency[].class);
			for (Currency value : currObjMapper) {
				currency = new Currency();
				currency.setCurrencyCode(value.getCurrencyCode());
				currency.setCurrencyRate(value.getCurrencyRate());
				currency.setCurrencyBase(value.getCurrencyBase());
				currency.setCurrencyDate(value.getCurrencyDate());
				listCurrency.add(currency);
			}
		} catch (JsonParseException jp) {
			jp.printStackTrace();
		} catch (JsonMappingException jp) {
			jp.printStackTrace();
		} catch (IOException jp) {
			jp.printStackTrace();
		}

		return listCurrency;
	}

	// This method will be used to display converted currency value to TextField.
	private void convertCurrency() {
		try {
			long startTime = System.currentTimeMillis();
			Double convertedCurr = CurrencyRateHelper.callCalculatedCurrRate(Double.valueOf(tfAmountFrom.getValue()),
					currencyFrom, currencyTo, loadAccessToken());
			lblTime.setCaption("Time taken for calculation: " + (System.currentTimeMillis() - startTime) + "ms");
			tfConvertedAmount.setValue(convertedCurr.toString());
			lblLiveExchangeRate.setCaption(
					"The results date: " + currencyDate + " | From: exchangeratesapi.io");
		} catch (NumberFormatException e) {
			tfAmountFrom.setComponentError(new UserError("Please enter numeric value"));
		}
	}

	// Alll validation will be handle in this method.
	private boolean validateDetails() {
		boolean isValid = true;
		tfAmountFrom.setComponentError(null);
		cbCurrencyFrom.setComponentError(null);
		cbCurrencyTo.setComponentError(null);

		if (tfAmountFrom.getValue() == "" || tfAmountFrom.getValue().trim().length() == 0) {
			tfAmountFrom.setComponentError(new UserError("Please enter amount."));
			isValid = false;
		}

		if (cbCurrencyFrom.getValue() == null) {
			cbCurrencyFrom.setComponentError(new UserError("Please select currency"));
			isValid = false;
		}

		if (cbCurrencyTo.getValue() == null) {
			cbCurrencyTo.setComponentError(new UserError("Please select currency"));
			isValid = false;
		}
		return isValid;
	}

	// This method will reset the components.
	private void resetFields() {
		lblTime.setCaption("");
		lblLiveExchangeRate.setCaption("With Live Exchange Rate");
		tfAmountFrom.setValue("");
		cbCurrencyFrom.setValue(null);
		tfConvertedAmount.setValue("");
		cbCurrencyTo.setValue(null);
		tfAmountFrom.setComponentError(null);
		cbCurrencyFrom.setComponentError(null);
		cbCurrencyTo.setComponentError(null);
	}

	@WebServlet(urlPatterns = "/*", name = "CurrencyConverterServlet", asyncSupported = true)
	@VaadinServletConfiguration(ui = CurrencyConverterUI.class, productionMode = false)
	public static class CurrencyConverterServlet extends VaadinServlet {
	}
}
