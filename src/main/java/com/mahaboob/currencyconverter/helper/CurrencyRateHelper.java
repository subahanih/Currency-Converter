package com.mahaboob.currencyconverter.helper;

import static io.restassured.RestAssured.given;
import org.json.JSONException;
import org.json.JSONObject;
import io.restassured.response.Response;

public class CurrencyRateHelper {

	// This method will return access token from Authorization server based on given
	// user(userName: admin, password: admin).
	public static String getAccessToken() throws JSONException {
		Response response = given().auth().preemptive().basic("client-currconverter", "client-currconverter")
				.contentType("application/x-www-form-urlencoded").log().all().formParam("grant_type", "password")
				.formParam("username", "admin").formParam("password", "admin").when()
				.post("http://localhost:8080/oauth/token");

		JSONObject jsonObject = new JSONObject(response.getBody().asString());
		String accessToken = jsonObject.get("access_token").toString();
		String tokenType = jsonObject.get("token_type").toString();
		System.out.println("Oauth Token with type " + tokenType + "   " + accessToken);
		return accessToken;
	}

	// This method will return all currency details which ever mentioned in the
	// https://api.exchangeratesapi.io/latest
	// via our Secured API
	public static String callGetAllCurrencyDetails(String accessToken) {
		Response response = given().auth().preemptive().oauth2(accessToken).contentType("application/json")
				.when()
				.get("http://localhost:8080/secured/getAllCurrencyDetails");
		String responseBody = response.getBody().asString();
		if (response.getStatusCode() >= 200 && response.getStatusCode() <= 299) {
			System.out.println("Success Response = " + responseBody);
		} else {
			System.out.println("Failure Response = {}" + responseBody);
		}

		return responseBody;
	}

	// This method will return calculated currency rate from API.
	public static Double callCalculatedCurrRate(Double amount, Double fromCurrency, Double toCurrency, String accessToken) {
		Response response = given().auth().preemptive().oauth2(accessToken).contentType("application/json").when()
				.get("http://localhost:8080/secured/getCalculatedCurrRate?amount=" + amount + "&fromCurrency="
						+ fromCurrency + "&toCurrency=" + toCurrency);
		Double responseBody = Double.valueOf(response.getBody().asString());
		if (response.getStatusCode() >= 200 && response.getStatusCode() <= 299) {
			System.out.println("Success Response = " + responseBody);
		} else {
			System.out.println("Failure Response = {}" + responseBody);
		}
		return responseBody;
	}

}
