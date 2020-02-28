package com.automation.cucumberjsoncomparsion.utils;

import static com.jayway.restassured.RestAssured.given;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jayway.restassured.response.Response;

/**
 * @author Sudharsan
 *
 */
public class ApiTrigger {

	static Response resp;
	static Map<String, Object> msMapResponse;
	public static String msActualResponse;

	static int statusCode;
	static String statusLine;
	static Map<String, Object> headers = new HashMap<String, Object>();

	/**
	 * Getting all the mandate parameters to form the request and invoke the api
	 * using jayway rest assured library
	 * Converting the Json strinfg to Hasp map for comparing the KEY & VALUE
	 * 
	 * @param getUri
	 * @param getPath
	 * @param getInputParam
	 * @param getHeaderParam
	 * @return
	 */
	public static Map<String, Object> getOperation(String getUri, String getPath, String getInputParam,
			Map<String, String> getHeaderParam) {

		String restUri = "" + getUri + getPath + getInputParam + "";
		System.out.println("The test URL for GET API --> " + restUri);
		resp = given().when().relaxedHTTPSValidation().headers(getHeaderParam).get(restUri);

		String responseCode = "200";

		int responseCodeAssert = Integer.parseInt(responseCode);
		statusCode = resp.getStatusCode();
		statusLine = resp.getStatusLine();

		if (statusCode == responseCodeAssert && statusLine.contains("OK")) {
			System.out.println("Test case contains, Status Code: " + statusCode + "; Status Line: " + statusLine);
			msActualResponse = resp.asString();
			System.out.println("The test response --> " + msActualResponse);
			System.out.println("\n");

			Gson gson = new Gson();
			Type type = new TypeToken<TreeMap<String, Object>>() {
			}.getType();
			msMapResponse = gson.fromJson(msActualResponse, type);

		} else {
			System.out
					.println("Test execution failed with, Status Code: " + statusCode + "; Status Line: " + statusLine);
			msActualResponse = resp.asString();
			System.out.println("The test response --> " + msActualResponse);
			System.out.println("\n");
			Gson gson = new Gson();
			Type type = new TypeToken<TreeMap<String, Object>>() {
			}.getType();
			msMapResponse = gson.fromJson(msActualResponse, type);
		}
		return msMapResponse;
	}
}
